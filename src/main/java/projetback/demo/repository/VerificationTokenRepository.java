package projetback.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projetback.demo.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    Optional<VerificationToken> findByToken(String token);

    @Query(value="DELETE FROM token WHERE student_id = :id", nativeQuery = true)
    Optional<VerificationToken> deletesalim(@Param("id") Long id);

}
