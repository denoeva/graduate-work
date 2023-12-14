package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth_register.Register;
import ru.skypro.homework.dto.user.SetNewPasswordDto;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final UserRepository userRepository;


    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder,
                           UserService userService,
                           UserRepository userRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());
        return true;
    }

    @Override
    public void updatePassword(SetNewPasswordDto newPassDto) {
        Users user = userService.findAuthUser().orElseThrow(/*UserNotFoundException::new*/);
        boolean pass = encoder.matches(newPassDto.getCurrentPassword(), user.getPassword());
        if (pass) {
            user.setPassword(encoder.encode(newPassDto.getNewPassword()));
            userRepository.save(user);
        }
    }
}
