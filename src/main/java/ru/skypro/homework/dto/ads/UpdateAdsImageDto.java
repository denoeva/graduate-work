package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.entity.ImageAds;

/**
 *Wrapper class to getting image from advertisements
 */

@Data
public class UpdateAdsImageDto {
    private String id;
    private byte[] image;

    public static UpdateAdsImageDto fromImageAds(ImageAds imageAds) {
        return new UpdateAdsImageDto();
    }
}
