package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.comment.GetAllCommentsDto;
import ru.skypro.homework.dto.comment.GetCommentDto;
import ru.skypro.homework.entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring"
)
public abstract class CommentMapper {
    @Mapping(target = "author", source = "comment.users.id")
    @Mapping(target = "authorFirstName", source = "comment.users.firstName")
    @Mapping(target = "authorImage", source = "comment.users.image.id")
    public abstract GetCommentDto commentToGetCommentDto (Comment comment);
    public abstract Comment getCommentDtoToComment (GetCommentDto getCommentDto);

    public GetAllCommentsDto getAllCommentsToGetAllCommentsDto(List<Comment> comments) {
        GetAllCommentsDto getAllCommentsDto = new GetAllCommentsDto();
        getAllCommentsDto.setCount(comments.size());
        getAllCommentsDto.setResults(comments.stream().map(this::commentToGetCommentDto).collect(Collectors.toList()));
        return getAllCommentsDto;
    }

    public List<Comment> getAllCommentsDtoToListOfComments (GetAllCommentsDto getCommentDto) {
        return getCommentDto.getResults().stream().map(this::getCommentDtoToComment).collect(Collectors.toList());
    }


    public abstract CreateOrUpdateCommentDto commentToCreateOrUpdateCommentDto (Comment comment);

    public abstract Comment createOrUpdateCommentDtoToComment (CreateOrUpdateCommentDto createOrUpdateCommentDto);

}
