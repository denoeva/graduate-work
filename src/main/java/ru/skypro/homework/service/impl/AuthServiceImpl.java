package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.auth_register.Register;
import ru.skypro.homework.dto.user.SetNewPasswordDto;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UserDetailsServiceImpl;
import ru.skypro.homework.service.AuthService;

/**
 * The class with methods to login and register users
 */

@Service
public class AuthServiceImpl implements AuthService {
    private final UserDetailsServiceImpl userService;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsServiceImpl userService,
                           PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * The method to login users
     */
    @Override
    public boolean login(String userName, String password) {
        if (!userService.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = userService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }


    /**
     * The method to register users
     */
    @Override
    public boolean register(Register register) {
        if (userService.userExists(register.getUsername())) {
            return false;
        }
        String password = encoder.encode(register.getPassword());
        userService.createUser(register, password);
        return true;
    }
    /**
     * The method to update user password
     */
    @Override
    public void updatePassword(SetNewPasswordDto newPassDto, String userName) {
        Users user = userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
        if (encoder.matches(newPassDto.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(newPassDto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}