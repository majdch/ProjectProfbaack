package projetback.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projetback.demo.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link,Long> {



    @Query(value="Select * from course where archived = :archived AND subject_id= :subjects", nativeQuery = true)

    List<Link> findsalim(@Param("archived") boolean archived, @Param("subjects") Long s);

    List<Link> findByArchived(@Param("archived") boolean archived);

}
