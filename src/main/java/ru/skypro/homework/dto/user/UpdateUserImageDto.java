package ru.skypro.homework.dto.user;

import lombok.Data;
import ru.skypro.homework.entity.ImageAds;

/**
 *Wrapper class to getting user avatar
 */

@Data
public class UpdateUserImageDto {
    private String id;
    private byte[] image;

    public static UpdateUserImageDto ImageAds(ImageAds imageAds) {
        return new UpdateUserImageDto();
    }
}
