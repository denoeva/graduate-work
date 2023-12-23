package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.UserInfoDto;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.service.UserService;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserInfoDto getInfoAboutUser() {
        return null;
    }

    @Override
    public UserInfoDto updateInfoAboutUser(UserInfoDto userInfoDto) {
        return null;
    }

    @Override
    public Optional<Users> findAuthUser() {
        return Optional.empty();
    }

    @Override
    public void updateUserImage(MultipartFile image) {

    }
}
