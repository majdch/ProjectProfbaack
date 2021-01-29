package projetback.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import  projetback.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {



    @Query(value="Select * from course where archived = :archived AND subject_id= :subjects", nativeQuery = true)

    List<Course> findsalim(@Param("archived") boolean archived, @Param("subjects") Long s);
    List<Course> findByArchived(@Param("archived") boolean archived);


}