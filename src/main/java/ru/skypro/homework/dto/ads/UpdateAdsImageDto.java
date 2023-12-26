package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Wrapper class to getting image from advertisements
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdsImageDto {
    private String id;
    private byte[] image;
}
