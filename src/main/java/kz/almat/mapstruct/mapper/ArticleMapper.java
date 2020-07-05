package kz.almat.mapstruct.mapper;

import kz.almat.mapstruct.dto.ArticleDto;
import kz.almat.mapstruct.dto.CommentDto;
import kz.almat.mapstruct.mapper.qualifier.ArticleCommentsMapper;
import kz.almat.mapstruct.model.Article;
import kz.almat.mapstruct.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "topic", target = "topic")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "comments", target = "commentDtos", qualifiedBy = ArticleCommentsMapper.class)
    ArticleDto articleToDto(Article article);

    List<ArticleDto> articlesToDtos(List<Article> article);

    @ArticleCommentsMapper
    public static List<CommentDto> commentsToDtos(List<Comment> comments) {
        return CommentMapper.INSTANCE.commentsToDtos(comments);
    }

}
