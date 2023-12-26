package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdsDto;
import ru.skypro.homework.dto.ads.GetAdsDto;
import ru.skypro.homework.dto.ads.GetAllAdsDto;
import ru.skypro.homework.dto.ads.GetFullInfoAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.ImageAd;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.AccessErrorException;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.ImageNotFoundException;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.mapper.AdsMapperImpl;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageAdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final ImageAdRepository imageAdRepository;

    private final AdsMapperImpl adsMapper;

    public AdsServiceImpl(AdsRepository adsRepository,
                          UserRepository userRepository,
                          ImageAdRepository imageAdRepository, AdsMapperImpl adsMapper) {
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.imageAdRepository = imageAdRepository;
        this.adsMapper = adsMapper;
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
        List<Ad> adList = adsRepository.findAllAds();
        return adsMapper.adsToGetAllAdsDto(adList);
    }

    @Override
    public GetAdsDto createAds(CreateOrUpdateAdsDto adsDto, MultipartFile imageFile, Authentication authentication) {
        if (authentication.isAuthenticated()) {

            //Ищем пользователя
            Users user = userRepository.findByUsername(authentication.getName()).orElseThrow(UserNotFoundException::new);

            Ad ad = adsMapper.createOrUpdateAdsDtoToAd(adsDto, new Ad());
            ad.setUser(user);


            ImageAd image = new ImageAd();
            image.setId(UUID.randomUUID().toString());
            try {
                byte[] imageBytes = imageFile.getBytes();
                image.setImage(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            ImageAd returnImage = imageAdRepository.save(image);

            ad.setImage(returnImage);

            return adsMapper.adsToDto(adsRepository.save(ad));

        } else {
            throw new AccessErrorException();
        }
    }

    @Override
    public GetFullInfoAdsDto getFullAdDto(Integer id, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Ad fullInfoAd = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
            return adsMapper.adToGetFullInfoAdsDto(fullInfoAd);
        } else {
            throw new AccessErrorException();
        }
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
            Ad adToUpdate = adsMapper.createOrUpdateAdsDtoToAd(adsDto, updatedAd);
            return adsMapper.adsToDto(adsRepository.save(adToUpdate));
        } else {
            throw new AccessErrorException();
        }
    }

    @Override
    public GetAllAdsDto getAllUserAdsDto(Authentication authentication) {
        Users user = userRepository.findByUsername(authentication.getName()).orElseThrow(UserNotFoundException::new);
        List<Ad> allUserAds = adsRepository.getAdsMe(user.getId());
        return adsMapper.adsToGetAllAdsDto(allUserAds);
    }

    @Override
    public byte[] getImage(String id) {
        ImageAd image = imageAdRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        return image.getImage();
    }

    @Override
    public void updateImageAdDto(int id, MultipartFile file, Authentication authentication) {
        Ad ad = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
        if (isAdminOrOwnerAd(authentication, ad.getUser().getUsername())) {
            ImageAd image;
            if (ad.getImage() != null) {
                image = imageAdRepository.findById(ad.getImage().getId()).orElse(new ImageAd());
            } else {
                image = new ImageAd();
                image.setId(UUID.randomUUID().toString());
            }
            try {
                byte[] imageBytes = file.getBytes();
                image.setImage(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image content" + e);
            }
            imageAdRepository.save(image);
            ad.setImage(image);
            adsRepository.save((ad));
        } else {
            throw new AccessErrorException();
        }
    }

    //TODO remove
    private boolean isUser(Authentication authentication) {
        boolean isUser = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_USER");
        return isUser;
    }
}
