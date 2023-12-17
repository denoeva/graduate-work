package ru.skypro.homework.dto.user;

import lombok.*;
import ru.skypro.homework.entity.ImageAd;

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

    public static UpdateUserImageDto ImageAds(ImageAd imageAd) {
        return new UpdateUserImageDto();
    }
}
