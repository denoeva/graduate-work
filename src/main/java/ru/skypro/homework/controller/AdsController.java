package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public GetAllAdsDto getAllAds() {
        return adsService.getAllAdsDto();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GetAdsDto addAd(@RequestPart CreateOrUpdateAdsDto properties,
                              @RequestPart MultipartFile image) {
        return adsService.createAds(properties, image);
    }

    @GetMapping("/{id}")
    public GetFullInfoAdsDto getAds(@PathVariable Integer id) {
        return adsService.getFullAdDto(id);
    }

    @DeleteMapping("/{id}")
    public boolean removeAd(@PathVariable Integer id) {
        return adsService.removeAdDto(id);
    }

    @PatchMapping("/{id}")
    public GetAdsDto updateAds(@PathVariable Integer id,
                               @RequestBody CreateOrUpdateAdsDto adsDto) {
        return adsService.updateAdDto(id, adsDto);
    }

    @GetMapping("/me")
    public GetAllAdsDto getAdsForMe() {
        return adsService.getAllUserAdsDto();
    }

    @PatchMapping("/{id}/image")
    public void updateImage(@PathVariable Integer id,
                            @RequestPart MultipartFile image) {
        adsService.updateImageAdDto(id, image);
    }

    //COMMENTS
    @GetMapping("/{id}/comments")
    public GetAllCommentsDto getComments(@PathVariable("id") int adId) {
        return commentService.getComments(adId);
    }

    @PostMapping("/{id}/comments")
    public GetCommentDto addComment(@PathVariable("id") int adId,
                                    @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDTO) {
        return commentService.addComment(adId, createOrUpdateCommentDTO);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<GetCommentDto> deleteComment(@PathVariable int adId,
                                                       @PathVariable int commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public GetAllCommentsDto updateComment(@PathVariable int adId,
                                           @PathVariable int commentId,
                                           @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDTO) {
        return commentService.updateComment(adId, commentId, createOrUpdateCommentDTO);
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("id") String id) {
        return imageService.getImage(id);
    }
}