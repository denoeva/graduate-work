package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper class for creating or updating the advertisement
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAdsDto {
    private String description;
    private int price;
    private String title;
}
