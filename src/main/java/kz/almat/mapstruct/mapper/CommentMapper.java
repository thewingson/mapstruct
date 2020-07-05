package kz.almat.mapstruct.mapper;

import kz.almat.mapstruct.dto.CommentDto;
import kz.almat.mapstruct.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Stream;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "article.id", target = "articleId")
    CommentDto commentToDto(Comment comment);

    List<CommentDto> commentsToDtos(List<Comment> comments);
    List<CommentDto> streamToDtos(Stream<Comment> comments);
}
