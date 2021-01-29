package projetback.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @NotBlank(message = "Name is required")
    private String studentName;
    @NotBlank(message = "Password is required")
    private String password;
    @Email
    @Column(unique = true)
    @NotEmpty(message = "Email is required")
    private String email;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "subjectId")
    private Subjects subjects;
    private Instant created;
    private boolean enabled;
    private boolean is_admin;
    private boolean Unexpired;


}
