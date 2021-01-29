package projetback.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long studentId;
    private String studentName;
    private String email;
    private String subjects_name;
    private Long subjects_id;
    private Instant created;
    private boolean enabled;
    private boolean is_admin;
    private boolean unexpired;
}
