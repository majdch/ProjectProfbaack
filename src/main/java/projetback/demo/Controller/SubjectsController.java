package projetback.demo.Controller;

import projetback.demo.dto.PostDto;
import projetback.demo.dto.SubjectsDto;
import projetback.demo.service.PostService;
import projetback.demo.service.SubjectsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
@Slf4j
public class SubjectsController {

    private final SubjectsService subjectsService;
    @PostMapping
    public ResponseEntity<SubjectsDto> CreatePost(@RequestBody SubjectsDto subjectsDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectsService.save(subjectsDto));
    }

    @GetMapping
    public ResponseEntity<List<SubjectsDto>> getAllposts() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectsService.getAll());
    }
}
