package projetback.demo.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @NotBlank(message = "Article name cannot be empty or Null")
    private String articleName;

    @NotBlank(message = "attachment cannot be empty or Null")
    private String attachment;

    @NotBlank(message = "content cannot be empty or Null")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "subjectId")
    private Subjects subjects;

    private Instant created;

    private boolean archived;
}
