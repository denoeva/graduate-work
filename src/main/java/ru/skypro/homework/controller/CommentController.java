package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.comment.GetAllCommentsDto;
import ru.skypro.homework.dto.comment.GetCommentDto;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    @GetMapping("/{id}/comments")
    public ResponseEntity<GetAllCommentsDto> getComments(@PathVariable("id") Integer adId,
                                                         Authentication authentication) {
        logger.info("get all comments");
        return ResponseEntity.ok(commentService.getComments(adId, authentication));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<GetCommentDto> addComment(@PathVariable("id") Integer adId,
                                                    @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateComment,
                                                    Authentication authentication) {
        logger.info("add new comment");
        return ResponseEntity.ok(commentService.addComment(adId, createOrUpdateComment, authentication));
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public void deleteComment(@PathVariable("adId") Integer adId,
                              @PathVariable("commentId") Integer commentId,
                              Authentication authentication) {
        logger.info("delete comment");
        commentService.deleteComment(adId, commentId, authentication);
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public GetCommentDto updateComment(@PathVariable("adId") Integer adId,
                                       @PathVariable("commentId") Integer commentId,
                                       @RequestBody @Valid CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                       Authentication authentication) {
        logger.info("update comment");
        return commentService.updateComment(adId, commentId, createOrUpdateCommentDto, authentication);
    }
}