package kz.almat.mapstruct.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ArticleDto {

    private Long id;
    private String topic;
    private String text;
    private List<CommentDto> commentDtos;

}
