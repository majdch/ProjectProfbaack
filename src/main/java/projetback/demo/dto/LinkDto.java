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
public class LinkDto {
    private Long linkId;
    private String linkName;
    private String url;
    private Long subjects;
    private String subjects_name;
    private boolean archived;
    private Instant created;
}
