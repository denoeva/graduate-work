package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;

/**
 * Interface with CRUD methods for advertisement
 */

public interface AdsService {
    GetAllAdsDto getAllAdsDto();
    GetAdsDto createAds(CreateOrUpdateAdsDto adsDto, MultipartFile image);
    GetFullInfoAdsDto getFullAdDto(Integer id);
    boolean removeAdDto(Integer id);
    GetAdsDto updateAdDto(Integer id, CreateOrUpdateAdsDto adsDto);
    GetAllAdsDto getAllUserAdsDto();
    void updateImageAdDto(Integer id, MultipartFile image);
    boolean checkAccess(Integer id);
}
