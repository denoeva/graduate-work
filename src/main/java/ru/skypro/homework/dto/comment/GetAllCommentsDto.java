package ru.skypro.homework.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Wrapper class to getting a list of comments and their number
 */

@Data
@AllArgsConstructor
public class GetAllCommentsDto {
    private int count;
    private List<GetCommentDto> results;

    public GetAllCommentsDto() {

    }
}
