package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdsDto;
import ru.skypro.homework.dto.ads.GetAdsDto;
import ru.skypro.homework.dto.ads.GetAllAdsDto;
import ru.skypro.homework.dto.ads.GetFullInfoAdsDto;
import ru.skypro.homework.service.AdsService;
@Service
public class AdsServiceImpl implements AdsService {
    @Override
    public GetAllAdsDto getAllAdsDto() {
        return null;
    }

    @Override
    public GetAdsDto createAds(CreateOrUpdateAdsDto adsDto, MultipartFile image) {
        return null;
    }

    @Override
    public GetFullInfoAdsDto getFullAdDto(Integer id) {
        return null;
    }

    @Override
    public boolean removeAdDto(Integer id) {
        return false;
    }

    @Override
    public GetAdsDto updateAdDto(Integer id, CreateOrUpdateAdsDto adsDto) {
        return null;
    }

    @Override
    public GetAllAdsDto getAllUserAdsDto() {
        return null;
    }

    @Override
    public void updateImageAdDto(Integer id, MultipartFile image) {

    }

    @Override
    public boolean checkAccess(Integer id) {
        return false;
    }
}
