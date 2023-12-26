package ru.skypro.homework.dto.user;

import lombok.*;

/**
 *Wrapper class to getting user avatar
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateUserImageDto {
    private String id;
    private byte[] image;
}
