package ru.skypro.homework.dto.ads;

import lombok.Data;

/**
 * Wrapper class to getting full information about the advertisement
 */

@Data
public class GetFullInfoAdsDto {
    private int pk;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String phone;
    private String image;
    private int price;
    private String title;
}
