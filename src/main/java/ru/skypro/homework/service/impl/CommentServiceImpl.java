package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.comment.GetAllCommentsDto;
import ru.skypro.homework.dto.comment.GetCommentDto;
import ru.skypro.homework.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public GetAllCommentsDto getComments(int adId) {
        return null;
    }

    @Override
    public GetCommentDto addComment(int adId, CreateOrUpdateCommentDto comment) {
        return null;
    }

    @Override
    public void deleteComment(int adId, int commentId) {

    }

    @Override
    public GetAllCommentsDto updateComment(int adId, int commentId, CreateOrUpdateCommentDto comment) {
        return null;
    }
}
