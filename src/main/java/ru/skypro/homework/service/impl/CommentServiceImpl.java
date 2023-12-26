package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    @Autowired
    CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              AdsRepository adsRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
    }

    private boolean isAdminOrOwnerComment(Authentication authentication, String ownerComment) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_ADMIN");

        boolean isOwnerComment = authentication.getName().equals(ownerComment);

        return isAdmin || isOwnerComment;

    }

    @Override
    public GetAllCommentsDto getComments(Integer adId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            List<Comment> comments = commentRepository.findAllCommentsByAdId(adId);
           return commentMapper.getAllCommentsToGetAllCommentsDto(comments);
        } else {
            throw new AccessErrorException();
        }
    }

    @Override
    public GetCommentDto addComment(Integer adId,
                                    CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                    Authentication authentication) {
        if (authentication.isAuthenticated()) {
            String username = authentication.getName();

            Ad getAd = adsRepository.findAdByPk(adId).orElseThrow(AdNotFoundException::new);
            Users meUsers = userRepository.findByUsername(username)
                    .orElseThrow(UserNotFoundException::new);
            Comment newComment = new Comment();
            newComment.setUsers(meUsers);
            newComment.setAd(getAd);
            newComment.setText(createOrUpdateCommentDto.getText());
            newComment.setCreatedAt(LocalDateTime.now());
            GetCommentDto getCommentDto = GetCommentDto.fromComment(commentRepository.save(newComment));
            return getCommentDto;
        } else {
            throw new AccessErrorException();
        }
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Comment findComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            if (!adId.equals(findComment.getAd().getPk())) {
                throw new CommentNotFoundException();
            } else {
                if (isAdminOrOwnerComment(authentication, findComment.getUsers().getUsername())) {
                    commentRepository.delete(findComment);
                } else {
                    throw new AccessErrorException();
                }
            }
        } else {
            throw new AccessErrorException();
        }

    }

    @Override
    public GetCommentDto updateComment(Integer adId, Integer commentId,
                                       CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                       Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Comment findComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            if (!adId.equals(findComment.getAd().getPk())) {
                throw new CommentNotFoundException();
            } else {
                if (isAdminOrOwnerComment(authentication, findComment.getUsers().getUsername())) {
                    findComment.setText(createOrUpdateCommentDto.getText());
                    findComment.setCreatedAt(LocalDateTime.now());
                    GetCommentDto commentDTO = GetCommentDto.fromComment(commentRepository.save(findComment));
                    return commentDTO;
                } else {
                    throw new AccessErrorException();
                }

            }
        } else {
            throw new AccessErrorException();
        }
    }
}
