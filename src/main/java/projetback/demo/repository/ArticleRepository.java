package projetback.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projetback.demo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    @Query(value="Select * from article where archived = :archived AND subject_id= :subjects", nativeQuery = true)

    List<Article> findsalim(@Param("archived") boolean archived,@Param("subjects") Long s);
    List<Article> findByArchived(@Param("archived") boolean archived);

}

