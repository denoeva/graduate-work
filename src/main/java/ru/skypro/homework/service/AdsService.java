package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;

/**
 * Interface with CRUD methods for advertisement
 */

public interface AdsService {
    GetAllAdsDto getAllAdsDto();

    GetAdsDto createAds(CreateOrUpdateAdsDto adsDto, MultipartFile image, Authentication authentication);

    GetFullInfoAdsDto getFullAdDto(Integer id, Authentication authentication);

    ResponseEntity<Void> removeAd(int id, Authentication authentication);

    GetAdsDto updateAdDto(Integer id, CreateOrUpdateAdsDto adsDto, Authentication authentication);

    GetAllAdsDto getAllUserAdsDto(Authentication authentication);

    UpdateAdsImageDto updateImageAdDto(int id, MultipartFile image, Authentication authentication);

    byte[] getImage(String id);
}
