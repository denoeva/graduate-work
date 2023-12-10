package ru.skypro.homework.dto.user;

import lombok.Data;

/**
 * Wrapper class to creating a new password
 */

@Data
public class SetNewPasswordDto {
    private String currentPassword;
    private String newPassword;
}
