package ru.skypro.homework.dto.comment;

import lombok.Data;

/**
 * Wrapper class for creating or updating the comment
 */

@Data
public class CreateOrUpdateCommentDto {
    private String text;
}
