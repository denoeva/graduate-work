package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper class to changing user data
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    private String firstName;
    private String lastName;
    private String phone;

}
