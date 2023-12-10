package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.SetNewPasswordDto;
import ru.skypro.homework.dto.user.UserInfoDto;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

/**
 * The class-controller for running user's endpoints
 */

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final ImageService imageService;
    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/set_password")
    public void updatePassword(
            @RequestBody SetNewPasswordDto newPassDto) {
        authService.updatePassword(newPassDto);
    }

    @GetMapping("/me")
    public UserInfoDto getUser() {
        return userService.getInfoAboutUser();
    }

    @PatchMapping("/me")
    public UserInfoDto updateInfoAboutUser(
            @RequestBody UserInfoDto userInfoDto) {
        return userService.updateInfoAboutUser(userInfoDto);
    }

    @PatchMapping("/me/image")
    public ResponseEntity<byte[]> updateUserImage(
            @RequestPart MultipartFile image) {
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("id") String id) {
        return imageService.getImage(id);
    }
}
