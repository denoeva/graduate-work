package ru.skypro.homework.dto.ads;

import lombok.Data;
import ru.skypro.homework.entity.ImageAd;

/**
 *Wrapper class to getting image from advertisements
 */

@Data
public class UpdateAdsImageDto {
    private String id;
    private byte[] image;

    public static UpdateAdsImageDto fromImageAds(ImageAd imageAd) {
        return new UpdateAdsImageDto();
    }
}
