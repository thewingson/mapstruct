package kz.almat.mapstruct.controller;


import kz.almat.mapstruct.dto.CommentDto;
import kz.almat.mapstruct.mapper.CommentMapper;
import kz.almat.mapstruct.model.Article;
import kz.almat.mapstruct.model.Comment;
import kz.almat.mapstruct.repo.ArticleRepo;
import kz.almat.mapstruct.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentRepo commentRepo;
    private final ArticleRepo articleRepo;

    @Autowired
    public CommentController(CommentRepo commentRepo, ArticleRepo articleRepo) {
        this.commentRepo = commentRepo;
        this.articleRepo = articleRepo;
    }

    @GetMapping
    public List<CommentDto> all(){
        return CommentMapper.INSTANCE.commentsToDtos(commentRepo.findAll());
    }

    @GetMapping("{id}")
    public CommentDto one(@PathVariable Long id){
        Optional<Comment> comment = commentRepo.findById(id);
        if(comment.isEmpty()){
            throw new RuntimeException("comment not found");
        }
        return CommentMapper.INSTANCE.commentToDto(comment.get());
    }

    @PostMapping("/articles/{articleId}")
    public void add(@PathVariable Long articleId, @RequestBody Comment comment){
        Optional<Article> article = articleRepo.findById(articleId);
        if(article.isEmpty()){
            throw new RuntimeException("article not found");
        }
        comment.setArticle(article.get());
        commentRepo.save(comment);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody Comment comment){
        Optional<Comment> byId = commentRepo.findById(id);
        if(byId.isPresent()){
            Comment comment1 = byId.get();
            comment1.setText(comment.getText());
            comment1.setArticle(comment.getArticle());
            commentRepo.save(comment1);
        }
    }

    @DeleteMapping("{id}")
    public void edit(@PathVariable Long id){
        commentRepo.deleteById(id);
    }
}
