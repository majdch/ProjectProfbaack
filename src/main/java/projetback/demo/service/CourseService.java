package projetback.demo.service;

import projetback.demo.dto.ArticleDto;
import projetback.demo.dto.CourseDto;
import projetback.demo.exceptions.SpringRedditException;
import projetback.demo.model.Article;
import projetback.demo.model.Course;
import projetback.demo.model.Subjects;
import projetback.demo.repository.ArticleRepository;
import projetback.demo.repository.CourseRepository;
import projetback.demo.repository.SubjectsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {
    private final CourseRepository courseRepository;
    private final SubjectsRepository subjectsRepository;

    @Transactional
    public CourseDto save(CourseDto courseDto) {
        Course course = mapCourseDto(courseDto);
        Course course1= courseRepository.save(course);
        courseDto.setCourseId(course1.getCourseId());
        courseDto.setCreated(course1.getCreated());
        courseDto.setSubjects(course1.getSubjects().getSubjectId());
        courseDto.setAttachment(course1.getAttachment());
        courseDto.setCourseName(course1.getCourseName());

        return courseDto;
    }

    private Course mapCourseDto(CourseDto courseDto) {
        Subjects subject = subjectsRepository.findById(courseDto.getSubjects()) .orElseThrow(() -> new SpringRedditException("Subject not found"));
        return Course.builder().courseId(courseDto.getCourseId()).archived(false).
                CourseName(courseDto.getCourseName()).subjects(subject).created(Instant.now()).Attachment(courseDto.getAttachment()).build();
    }
    @Transactional(readOnly = true)
    public List<CourseDto>  getAll(Long id) {
        return courseRepository.findsalim(false,id).stream().map(this::mapCourseDto).collect(Collectors.toList());
    }

    private CourseDto mapCourseDto(Course course) {
        return CourseDto.builder().CourseName(course.getCourseName()).archived(course.isArchived()).subjects_name(course.getSubjects().getSubjectName()).subjects(course.getSubjects().getSubjectId()).courseId(course.getCourseId()).Attachment(course.getAttachment()).created(course.getCreated()).build();

    }

    public CourseDto getArticle(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(()->new SpringRedditException("No article found"));
        CourseDto courseDto =new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setSubjects(course.getSubjects().getSubjectId());
        courseDto.setCreated(course.getCreated());
        courseDto.setAttachment(course.getAttachment());
        courseDto.setCourseName(course.getSubjects().getSubjectName());
        courseDto.setArchived(course.isArchived());
        return courseDto;
    }

    public Course ArchiverCourse(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(()->new SpringRedditException("Course not found"));
        course.setArchived(!course.isArchived());
        Course course1=courseRepository.save(course);
        return course1;
    }

    public CourseDto updateCourse(CourseDto courseDto) {
        Course course=courseRepository.findById(courseDto.getCourseId()).orElseThrow(()->new SpringRedditException("Course not found"));
        course.setCourseName(courseDto.getCourseName());
        course.setAttachment(courseDto.getAttachment());
        Subjects subjects=subjectsRepository.findById(courseDto.getSubjects()).orElseThrow(()->new SpringRedditException("Subject not found"));
        course.setSubjects(subjects);
        course.setCreated(Instant.now());
        course.setArchived(course.isArchived());
        courseRepository.save(course);
        return courseDto;

    }
}