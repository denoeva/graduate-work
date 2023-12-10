package ru.skypro.homework.dto.user;

import lombok.*;
import ru.skypro.homework.dto.auth_register.Role;

/**
 * The class-wrapper for outputting data in user's profile
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;
}
