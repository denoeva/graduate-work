package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.comment.GetAllCommentsDto;
import ru.skypro.homework.dto.comment.GetCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.AccessErrorException;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.CommentNotFoundException;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Service class to manage comments
 */
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              AdsRepository adsRepository,
                              UserRepository userRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }
    /**
     * The method to check permissions to manage comments
     */
    private boolean isAdminOrOwnerComment(Authentication authentication, String ownerComment) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_ADMIN");

        boolean isOwnerComment = authentication.getName().equals(ownerComment);

        return isAdmin || isOwnerComment;

    }
    /**
     * The method to find all existing comments
     */
    @Override
    public GetAllCommentsDto getComments(Integer adId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            List<Comment> comments = commentRepository.findAllCommentsByAdId(adId);
           return commentMapper.getAllCommentsToGetAllCommentsDto(comments);
        } else {
            throw new AccessErrorException("Operation is not allowed to unauthorized users");
        }
    }
    /**
     * The method to add new comment
     */
    @Override
    public GetCommentDto addComment(Integer adId,
                                    CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                    Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Ad getAd = adsRepository.findAdByPk(adId).orElseThrow(AdNotFoundException::new);
            Users user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(UserNotFoundException::new);
            Comment newComment =  commentMapper.createOrUpdateCommentDtoToComment(createOrUpdateCommentDto);
            newComment.setUsers(user);
            newComment.setAd(getAd);
            newComment.setCreatedAt(LocalDateTime.now());
            return commentMapper.commentToGetCommentDto(commentRepository.save(newComment));
        } else {
            throw new AccessErrorException("Operation is not allowed to unauthorized users");
        }
    }
    /**
     * The method to delete comment
     */
    @Override
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Comment commentToDelete = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            if (!adId.equals(commentToDelete.getAd().getPk())) {
                throw new CommentNotFoundException();
            } else {
                if (isAdminOrOwnerComment(authentication, commentToDelete.getUsers().getUsername())) {
                    commentRepository.delete(commentToDelete);
                } else {
                    throw new AccessErrorException("Delete operation is not allowed, insufficient permission");
                }
            }
        } else {
            throw new AccessErrorException("Operation is not allowed to unauthorized users");
        }

    }
    /**
     * The method to update existing comment
     */
    @Override
    public GetCommentDto updateComment(Integer adId, Integer commentId,
                                       CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                       Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Comment commentToUpdate = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            if (adId.equals(commentToUpdate.getAd().getPk()) && isAdminOrOwnerComment(authentication, commentToUpdate.getUsers().getUsername())) {
                    commentToUpdate.setText(createOrUpdateCommentDto.getText());
                    commentToUpdate.setCreatedAt(LocalDateTime.now());
                    return commentMapper.commentToGetCommentDto(commentRepository.save(commentToUpdate));
            } else {
                throw new AccessErrorException("Update is not allowed, insufficient permission");
            }
        } else {
            throw new AccessErrorException("Operation is not allowed to unauthorized users");
        }
    }
}
