package ru.skypro.homework.dto.comment;

import lombok.Data;

import java.util.List;

/**
 * Wrapper class to getting a list of comments and their number
 */

@Data
public class GetAllCommentsDto {
    private int count;
    private List<GetCommentDto> results;
}
