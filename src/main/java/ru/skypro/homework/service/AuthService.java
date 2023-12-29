package ru.skypro.homework.service;

import ru.skypro.homework.dto.auth_register.Register;
import ru.skypro.homework.dto.user.SetNewPasswordDto;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(Register register);
    void updatePassword(SetNewPasswordDto newPassDto, String name);
}
