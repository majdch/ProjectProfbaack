package projetback.demo.Controller;

import projetback.demo.model.Article;
import projetback.demo.model.Course;
import projetback.demo.model.Link;
import projetback.demo.model.Post;
import projetback.demo.service.ArticleService;
import projetback.demo.service.CourseService;
import projetback.demo.service.Linkservice;
import projetback.demo.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/archiver")
@AllArgsConstructor
@Slf4j
public class ArchiverController {
    private final ArticleService articleService;
    private final PostService postService;
    private final Linkservice linkservice;
    private final CourseService courseService;

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> ArchiverCourse(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.ArchiverCourse(id));
    }

    @GetMapping("/link/{id}")
    public ResponseEntity<Link> ArchiverLink(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(linkservice.ArchiverLink(id));
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<Article> ArchiverArticle(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.ArchiveArticle(id));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> ArchiverPost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.ArchivePost(id));
    }
}