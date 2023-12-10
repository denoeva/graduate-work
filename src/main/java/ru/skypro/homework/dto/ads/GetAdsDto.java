package ru.skypro.homework.dto.ads;

import lombok.Data;

/**
 *Wrapper class to getting information about the advertisement
 */

@Data
public class GetAdsDto {
    private int pk;
    private int author;
    private String image;
    private int price;
    private String title;
}
