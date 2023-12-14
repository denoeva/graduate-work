package ru.skypro.homework.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Wrapper class to getting a list of comments and their number
 */

@Data
public class GetCommentDto {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private int pk;
    private String text;
}
