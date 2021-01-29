package projetback.demo.dto;

import projetback.demo.model.Subjects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDto {
    private Long articleId;
    private String articleName;
    private String attachment;
    private String content;
    private Long subjects;
    private String subjects_name;
    private Instant created;
    private boolean archived;
}
