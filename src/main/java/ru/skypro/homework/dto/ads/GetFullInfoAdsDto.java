package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper class to getting full information about the advertisement
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
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
