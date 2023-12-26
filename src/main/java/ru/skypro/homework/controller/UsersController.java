package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.SetNewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserInfoDto;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

/**
 * The class-controller for running user's endpoints
 */

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final ImageService imageService;
    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/set_password")
    public void updatePassword(@RequestBody @Valid SetNewPasswordDto newPassDto,
                               Authentication authentication) {
        authService.updatePassword(newPassDto, authentication.getName());
    }

    @GetMapping("/me")
    public UserInfoDto getUser(Authentication authentication) {
        return userService.getInfoAboutUser(authentication.getName());
    }

    @PatchMapping("/me")
    public UserInfoDto updateInfoAboutUser(@RequestBody @Valid UpdateUserDto userInfoDto,
                                           Authentication authentication) {
        return userService.updateInfoAboutUser(userInfoDto, authentication.getName());
    }

    @PatchMapping("/me/image")
    public ResponseEntity<byte[]> updateUserImage(@RequestPart MultipartFile image,
                                                  Authentication authentication) {
        userService.updateUserImage(image, authentication.getName());
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte[] getImage(@PathVariable("id") String id) {
        return imageService.getImage(id);
    }
}
