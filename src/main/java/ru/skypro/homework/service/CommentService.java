package ru.skypro.homework.service;

import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.comment.GetAllCommentsDto;
import ru.skypro.homework.dto.comment.GetCommentDto;

/**
 * Interface with CRUD methods for comments
 */

public interface CommentService {
    GetAllCommentsDto getComments(int adId);
    GetCommentDto addComment(int adId, CreateOrUpdateCommentDto comment);
    void deleteComment(int adId, int commentId);
    GetAllCommentsDto updateComment(int adId, int commentId, CreateOrUpdateCommentDto comment);
}
