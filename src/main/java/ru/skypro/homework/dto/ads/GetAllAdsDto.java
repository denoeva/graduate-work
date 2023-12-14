package ru.skypro.homework.dto.ads;

import lombok.Data;

import java.util.List;

/**
 *Wrapper class to getting a list of advertisements and their number
 */

@Data
public class GetAllAdsDto {
    private int count;
    private List<GetAdsDto> results;

    public GetAllAdsDto(List<GetAdsDto> results) {
        this.results = results;
        this.count = results.size();
    }
}
