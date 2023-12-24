package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.comment.GetAllCommentsDto;
import ru.skypro.homework.dto.comment.GetCommentDto;

/**
 * Interface with CRUD methods for comments
 */

public interface CommentService {
    GetAllCommentsDto getComments(Integer adId,
                                  Authentication authentication);

    GetCommentDto addComment(Integer adId,
                             CreateOrUpdateCommentDto createOrUpdateCommentDto,
                             Authentication authentication);

    void deleteComment(Integer adId,
                       Integer commentId,
                       Authentication authentication);

    GetCommentDto updateComment(Integer adId,
                                Integer commentId,
                                CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                Authentication authentication);
}
