package ru.skypro.homework.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Wrapper class to getting a list of comments and their number
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCommentsDto {
    private int count;
    private List<GetCommentDto> results;
}
