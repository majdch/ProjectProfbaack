package projetback.demo.Controller;

import projetback.demo.dto.LinkDto;
import projetback.demo.dto.PostDto;
import projetback.demo.service.Linkservice;
import projetback.demo.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/link")
@AllArgsConstructor
@Slf4j
public class LinkController {
    private final Linkservice linkService;
    @PostMapping
    public ResponseEntity<LinkDto> CreateLink(@RequestBody LinkDto linkDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(linkService.save(linkDto));
    }
    @PutMapping
    public ResponseEntity<LinkDto> UpdateLink(@RequestBody LinkDto linkDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(linkService.update(linkDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<LinkDto>> getAlllinks(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(linkService.getAll(id));
    }
}
