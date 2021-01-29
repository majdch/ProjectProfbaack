package projetback.demo.service;

import projetback.demo.dto.SubjectsDto;
import projetback.demo.model.Post;
import projetback.demo.model.Subjects;
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
public class SubjectsService {
    private final SubjectsRepository subjectsRepository;

    @Transactional
    public SubjectsDto save(SubjectsDto subjectsDto) {
        Subjects subjects = mapSubjectsDto(subjectsDto);
        Subjects subjects1= subjectsRepository.save(subjects);
        subjectsDto.setSubjectId(subjects1.getSubjectId());
        subjectsDto.setCreated(subjects1.getCreated());

        subjectsDto.setSubjectName(subjects1.getSubjectName());
        return subjectsDto;
    }

    private Subjects mapSubjectsDto(SubjectsDto subjectsDto) {
        return Subjects.builder().subjectId(subjectsDto.getSubjectId()).subjectName(subjectsDto.getSubjectName()).created(Instant.now()).build();
    }

    @Transactional(readOnly = true)
    public List<SubjectsDto> getAll() {
        return subjectsRepository.findAll().stream().map(this::mapSubjectDto).collect(Collectors.toList());

    }

    private SubjectsDto mapSubjectDto(Subjects subjects) {
        return SubjectsDto.builder().subjectId(subjects.getSubjectId()).subjectName(subjects.getSubjectName()).created(Instant.now()).build();
    }
}
