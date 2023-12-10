package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.SetNewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UpdateUserImageDto;
import ru.skypro.homework.dto.user.UserInfoDto;
import ru.skypro.homework.entity.Users;

import java.util.Optional;

public interface UserService {
    UserInfoDto getInfoAboutUser();
    UserInfoDto updateInfoAboutUser(UserInfoDto userInfoDto);
    Optional<Users> findAuthUser();
    void updateUserImage(MultipartFile image);
}
