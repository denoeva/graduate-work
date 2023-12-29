package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.ads.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.ImageAd;
import ru.skypro.homework.repository.ImageAdRepository;

import java.util.List;
import java.util.stream.Collectors;
@Mapper(
        componentModel = "spring"
)
public abstract class AdsMapper {

    @Autowired
    ImageAdRepository imageAdRepository;
    @Mapping(target = "author", source = "user.id")
    public abstract GetAdsDto adsToDto (Ad ad);
    public abstract Ad DtoToAd (GetAdsDto getAdsDto);

    public abstract Ad createOrUpdateAdsDtoToAd (CreateOrUpdateAdsDto adsDto, @MappingTarget Ad ad);

    public GetAllAdsDto adsToGetAllAdsDto (List<Ad> ads) {
        GetAllAdsDto getAllAdsDto = new GetAllAdsDto();
        getAllAdsDto.setCount(ads.size());
        getAllAdsDto.setResults(ads.stream().map(this::adsToDto).collect(Collectors.toList()));
        return getAllAdsDto;
    }

    public List<Ad> getAllAdsDtoToAds (GetAllAdsDto getAllAdsDto) {
        return getAllAdsDto.getResults().stream().map(this::DtoToAd).collect(Collectors.toList());
    }
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "email", source = "user.username")
    public abstract GetFullInfoAdsDto adToGetFullInfoAdsDto (Ad ad);

    public void updateAdsImageDtoToAd (UpdateAdsImageDto updateAdsImageDto) {
        ImageAd imageAd = imageAdRepository.findById(updateAdsImageDto.getId()).orElseThrow(RuntimeException::new);
        imageAd.setImage(updateAdsImageDto.getImage());
        imageAdRepository.save(imageAd);
    }


    protected String map(ImageAd value) {
        if (value != null) {
            return "/ads/image/" + value.getId();
        } else {
            return "";
        }
    }

    protected ImageAd mapImage(String value) {
        return imageAdRepository.findById(value).orElseThrow(RuntimeException::new);
    }

    protected ImageAd mapBytes(byte[] value) {
        return imageAdRepository.findByImage(value).orElseThrow(RuntimeException::new);
    }

}
