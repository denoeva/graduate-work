package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Ad;

import java.util.Optional;

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

    public static GetAdsDto fromAd(Ad ad) {
        GetAdsDto adDTO = new GetAdsDto();
        adDTO.setPk(ad.getPk());
        adDTO.setAuthor(ad.getUser().getId());
        adDTO.setTitle(ad.getTitle());
        adDTO.setPrice(ad.getPrice());
        Optional.ofNullable(ad.getImage()).ifPresent(image -> adDTO.setImage(
                "/ads/" + ad.getImage().getId() + "/image"));
        return adDTO;
    }
}
