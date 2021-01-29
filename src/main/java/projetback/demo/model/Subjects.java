package projetback.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;
    @NotBlank(message = "Subject name cannot be empty or Null")
    private String subjectName;
    private Instant created;
}
