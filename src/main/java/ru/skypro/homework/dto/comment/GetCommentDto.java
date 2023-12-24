package ru.skypro.homework.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.entity.Comment;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Wrapper class to getting a list of comments and their number
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCommentDto {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private int pk;
    private String text;

    public static GetCommentDto fromComment(Comment comment) {
        GetCommentDto commentDTO = new GetCommentDto();
        commentDTO.setPk(comment.getPk());
        commentDTO.setAuthor(comment.getUsers().getId());
        Optional.ofNullable(comment.getUsers().getImage()).ifPresent(image -> commentDTO.setAuthorImage(
                "/users/" + comment.getUsers().getImage().getId() + "/image"));
        commentDTO.setAuthorFirstName(comment.getUsers().getFirstName());
        commentDTO.setCreatedAt(comment.getCreatedAt());
        commentDTO.setText(comment.getText());
        return commentDTO;
    }

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setPk(this.getPk());
        comment.setCreatedAt(this.getCreatedAt());
        comment.setText(this.getText());
        return comment;
    }
}
