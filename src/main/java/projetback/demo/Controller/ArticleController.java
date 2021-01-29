package projetback.demo.Controller;

import projetback.demo.dto.ArticleDto;
import projetback.demo.dto.LinkDto;
import projetback.demo.model.Article;
import projetback.demo.service.ArticleService;
import projetback.demo.service.Linkservice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/article")
@AllArgsConstructor
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping
    public ResponseEntity<ArticleDto> CreateLink(@RequestBody ArticleDto articleDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.save(articleDto));
    }
    @PutMapping
    public ResponseEntity<ArticleDto> UpdateLink(@RequestBody ArticleDto articleDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.update(articleDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<ArticleDto>> getAlllinks(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAll(id));
    }
    @GetMapping("/getcurrent/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getArticle(id));
    }
}
