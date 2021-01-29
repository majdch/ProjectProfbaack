package projetback.demo.model;


import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linkId;
    @NotBlank(message = "Link name cannot be empty or Null")
    private String linkName;
    @NotBlank(message = "Url name cannot be empty or Null")
    private String url;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "subjectId")
    private Subjects subjects;
    private Instant created;
    private boolean archived;
}
