package projetback.demo.Controller;


import projetback.demo.dto.PostDto;
import projetback.demo.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> CreatePost(@RequestBody PostDto postDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postDto));
    }
    @PutMapping
    public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.update(postDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PostDto>> getAllposts(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAll(id));
    }

    @GetMapping("getcurrent/{id}")
    public ResponseEntity<PostDto> getpost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getpost(id));
    }
}


