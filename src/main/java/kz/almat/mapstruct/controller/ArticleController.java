package kz.almat.mapstruct.controller;

import kz.almat.mapstruct.dto.ArticleDto;
import kz.almat.mapstruct.mapper.ArticleMapper;
import kz.almat.mapstruct.model.Article;
import kz.almat.mapstruct.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleRepo articleRepo;

    @Autowired
    public ArticleController(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @GetMapping
    public List<ArticleDto> all(){
        return ArticleMapper.INSTANCE.articlesToDtos(articleRepo.findAll());
    }

    @GetMapping("{id}")
    public ArticleDto one(@PathVariable Long id){
        Optional<Article> article = articleRepo.findById(id);
        if(article.isEmpty()){
            throw new RuntimeException("article not found");
        }
        return ArticleMapper.INSTANCE.articleToDto(article.get());
    }

    @PostMapping
    public void add(@RequestBody Article article){
        articleRepo.save(article);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody Article article){
        Optional<Article> byId = articleRepo.findById(id);
        if(byId.isPresent()){
            Article article1 = byId.get();
            article1.setText(article.getText());
            article1.setTopic(article.getTopic());
            articleRepo.save(article1);
        }
    }

    @DeleteMapping("{id}")
    public void edit(@PathVariable Long id){
        articleRepo.deleteById(id);
    }


}
