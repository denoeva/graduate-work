package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper class to creating a new password
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetNewPasswordDto {
    private String currentPassword;
    private String newPassword;
}
