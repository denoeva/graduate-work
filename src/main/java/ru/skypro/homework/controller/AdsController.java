package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ads.CreateOrUpdateAdsDto;
import ru.skypro.homework.dto.ads.GetAdsDto;
import ru.skypro.homework.dto.ads.GetAllAdsDto;
import ru.skypro.homework.dto.ads.GetFullInfoAdsDto;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.comment.GetAllCommentsDto;
import ru.skypro.homework.dto.comment.GetCommentDto;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

import javax.validation.Valid;

/**
 * The class-controller for running ads endpoints
 */

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdsController {
    private final AdsService adsService;
    private final ImageService imageService;
    private final CommentService commentService;

    /**
     * Getting all ads
     */
    @GetMapping()
    public GetAllAdsDto getAllAds() {
        return adsService.getAllAdsDto();
    }

    /**
     * Adding ads
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GetAdsDto> addAd(@RequestPart("properties") @Valid CreateOrUpdateAdsDto properties,
                                       @RequestPart("image") MultipartFile image,
                                       Authentication authentication) {
        return ResponseEntity.ok(adsService.createAds(properties, image, authentication));
    }

    /**
     * Getting info about ads
     */
    @GetMapping("/{id}")
    public GetFullInfoAdsDto getAds(@PathVariable int id, Authentication authentication) {
        return adsService.getFullAdDto(id, authentication);
    }

    /**
     * Deleting ads
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAdDto(@PathVariable int id, Authentication authentication) {
        return adsService.removeAd(id, authentication);
    }

    /**
     * Updating info about ads
     */
    @PatchMapping("/{id}")
    public GetAdsDto updateAdDto(@PathVariable Integer id,
                               @RequestBody CreateOrUpdateAdsDto adsDto,
                               Authentication authentication) {
        return adsService.updateAdDto(id, adsDto, authentication);
    }

    /**
     * Getting ads authorizied user
     */
    @GetMapping("/me")
    public GetAllAdsDto getAdsForMe(Authentication authentication) {
        return adsService.getAllUserAdsDto(authentication);
    }

    /**
     * Updating image in ads
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable int id,
                                              @RequestPart("image") MultipartFile image,
                                              Authentication authentication) {
        adsService.updateImageAdDto(id, image, authentication);
        return ResponseEntity.ok().build();
    }


    /**
     * Getting image from ads
     */
    @GetMapping(value = "/{id}/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte[] getImage(@PathVariable("id") String id) {
        return adsService.getImage(id);
    }
}