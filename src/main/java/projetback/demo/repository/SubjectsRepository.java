package projetback.demo.repository;

import projetback.demo.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {
    Optional<Subjects> findById(Long id);

}
