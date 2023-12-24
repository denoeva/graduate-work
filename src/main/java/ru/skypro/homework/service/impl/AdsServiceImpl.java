package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.*;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.ImageAd;
import ru.skypro.homework.exceptions.AccessErrorException;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageAdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final ImageAdRepository imageAdRepository;

    public AdsServiceImpl(AdsRepository adsRepository,
                          UserRepository userRepository,
                          ImageAdRepository imageAdRepository) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.imageAdRepository = imageAdRepository;
    }

    private boolean isAdminOrOwnerAd(Authentication authentication, String ownerAd) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_ADMIN");

        boolean isOwnerAd = authentication.getName().equals(ownerAd);

        return isAdmin || isOwnerAd;
    }

    @Override
    public GetAllAdsDto getAllAdsDto() {
        return null;
    }

    @Override
    public GetAdsDto createAds(CreateOrUpdateAdsDto adsDto, MultipartFile image, Authentication authentication) {
        return null;
    }

    @Override
    public GetFullInfoAdsDto getFullAdDto(Integer id, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeAd(int id, Authentication authentication) {
        Ad deletedAd = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
        if (isAdminOrOwnerAd(authentication, deletedAd.getUser().getUsername())) {
            adsRepository.delete(deletedAd);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new AccessErrorException();
        }
    }

    @Override
    public GetAdsDto updateAdDto(Integer id, CreateOrUpdateAdsDto adsDto, Authentication authentication) {
        Ad updatedAd = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
        if (isAdminOrOwnerAd(authentication, updatedAd.getUser().getUsername())) {
            updatedAd.setTitle(updatedAd.getTitle());
            updatedAd.setPrice(updatedAd.getPrice());
            updatedAd.setDescription(updatedAd.getDescription());
            adsRepository.save(updatedAd);
        } else {
            throw new AccessErrorException();
        }
        return GetAdsDto.fromAd(updatedAd);
    }

    @Override
    public GetAllAdsDto getAllUserAdsDto(Authentication authentication) {
        return null;
    }

    @Override
    public byte[] getImage(String id) {
        return new byte[0];
    }

    @Override
    public UpdateAdsImageDto updateImageAdDto(int id, MultipartFile file, Authentication authentication) {
        Ad ad = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
        if (isAdminOrOwnerAd(authentication, ad.getUser().getUsername())) {
            ImageAd image;
            if (!Objects.isNull(ad.getImage())) {
                image = imageAdRepository.findById(ad.getImage().getId()).orElse(new ImageAd());
            } else {
                image = new ImageAd();
                image.setId(ad.getPk().toString());
            }
            try {
                byte[] imageBytes = file.getBytes();
                image.setImage(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            ImageAd returnImage = imageAdRepository.save(image);
            ad.setImage(image);
            adsRepository.save((ad));
            return UpdateAdsImageDto.fromImageAds(returnImage);
        } else {
            throw new AccessErrorException();
        }
    }

    private boolean isUser(Authentication authentication) {
        boolean isUser = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_USER");
        return isUser;
    }
}
