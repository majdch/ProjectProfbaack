package projetback.demo.Controller;

import projetback.demo.dto.ArticleDto;
import projetback.demo.dto.CourseDto;
import projetback.demo.service.ArticleService;
import projetback.demo.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
@Slf4j
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    public ResponseEntity<CourseDto> CreateLink(@RequestBody CourseDto courseDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CourseDto>> getAlllinks(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll(id));
    }
    @GetMapping("/getcurrent/{id}")
    public ResponseEntity<CourseDto> getArticle(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getArticle(id));
    }

    @PutMapping
    public ResponseEntity<CourseDto> getArticle(@RequestBody CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(courseDto));
    }
}
