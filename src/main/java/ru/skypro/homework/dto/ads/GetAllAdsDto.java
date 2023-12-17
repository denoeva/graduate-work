package ru.skypro.homework.dto.ads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *Wrapper class to getting a list of advertisements and their number
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAdsDto {
    private int count;
    private List<GetAdsDto> results;

    public GetAllAdsDto(List<GetAdsDto> results) {
        this.results = results;
        this.count = results.size();
    }
}
