package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Wrapper class to getting information about the advertisement
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAdsDto {
    private int author;
    private String image;
    private int pk;

    private int price;
    private String title;

}
