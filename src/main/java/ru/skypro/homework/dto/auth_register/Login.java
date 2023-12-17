package ru.skypro.homework.dto.auth_register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private String username;
    private String password;
}
