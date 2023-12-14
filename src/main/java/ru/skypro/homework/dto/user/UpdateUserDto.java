package ru.skypro.homework.dto.user;

import lombok.Data;
import ru.skypro.homework.entity.Users;

/**
 * Wrapper class to changing user data
 */

@Data
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String phone;

}
