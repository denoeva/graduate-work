package ru.skypro.homework.dto.ads;

import lombok.Data;

/**
 * Wrapper class for creating or updating the advertisement
 */

@Data
public class CreateOrUpdateAdsDto {
    private String description;
    private int price;
    private String title;
}
