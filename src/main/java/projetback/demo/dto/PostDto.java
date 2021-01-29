package projetback.demo.dto;

import projetback.demo.model.Subjects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long postId;
    private String postName;
    private String content;
    private Long subjects;
    private Instant created;

}
