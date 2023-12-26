package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserInfoDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserInfoDto getInfoAboutUser(String userName) {
        Users user = userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
        return userMapper.userToUserInfoDto(user);
    }

    @Override
    public UserInfoDto updateInfoAboutUser(UpdateUserDto userInfoDto, String userName) {
        Users user = userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
        Users updatedUser = userMapper.updateUserDtoToUSer(userInfoDto, user);
        userRepository.save(updatedUser);
        return userMapper.userToUserInfoDto(updatedUser);
    }

    @Override
    public Optional<Users> findAuthUser() {
        return Optional.empty();
    }

    @Override
    public void updateUserImage(MultipartFile newImage, String userName) {
        Users user = userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
        Image image;
        if (user.getImage() != null) {
            image = imageRepository.findById(user.getImage().getId()).orElse(new Image());
        } else {
            image = new Image();
            image.setId(UUID.randomUUID().toString());
        }
        try {
            byte[] imageBytes = newImage.getBytes();
            image.setImage(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file" + e);
        }
        imageRepository.save(image);
        user.setImage(image);
        userRepository.save(user);
    }
}
