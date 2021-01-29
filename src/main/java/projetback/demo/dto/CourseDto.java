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
public class CourseDto {
    private Long courseId;
    private String CourseName;
    private String Attachment;
    private Long subjects;
    private String subjects_name;
    private Instant created;
    private boolean archived;
}
