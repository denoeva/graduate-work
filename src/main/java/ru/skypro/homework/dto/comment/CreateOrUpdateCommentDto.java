package ru.skypro.homework.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Wrapper class for creating or updating the comment
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateCommentDto {
    private String text;
}
