package projetback.demo.service;

import projetback.demo.dto.LinkDto;
import projetback.demo.dto.PostDto;
import projetback.demo.exceptions.SpringRedditException;
import projetback.demo.model.Link;
import projetback.demo.model.Post;
import projetback.demo.model.Subjects;
import projetback.demo.repository.LinkRepository;
import projetback.demo.repository.PostRepository;
import projetback.demo.repository.SubjectsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetback.demo.security.JwtAuthenticationFilter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class Linkservice {
    private final LinkRepository linkRepository;
    private final SubjectsRepository subjectsRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;



    @Transactional
    public LinkDto save(LinkDto linkDto){

        Link link = mapPostDto(linkDto);
        Link link1= linkRepository.save(link);
        linkDto.setLinkId(link1.getLinkId());
        linkDto.setCreated(link1.getCreated());
        linkDto.setSubjects(link1.getSubjects().getSubjectId());
        return linkDto;
    }
    public Link ArchiverLink(Long id) {
        Link link = linkRepository.findById(id).orElseThrow(()->new SpringRedditException("Link not found"));
        link.setArchived(!link.isArchived());
        Link link1 = linkRepository.save(link);
        return link1;
    }
    public LinkDto update(LinkDto linkDto) {
        Link link=linkRepository.findById(linkDto.getLinkId()).orElseThrow(()->new SpringRedditException("Link not found"));
        link.setCreated(Instant.now());
        link.setLinkName(linkDto.getLinkName());
        link.setUrl(linkDto.getUrl());
        Subjects subjects = subjectsRepository.findById(linkDto.getSubjects()).orElseThrow(()->new SpringRedditException("Subject not found"));
        link.setSubjects(subjects);
        linkRepository.save(link);
        return linkDto;
    }
    @Transactional(readOnly = true)
    public List<LinkDto> getAll(Long id) {
        return linkRepository.findsalim(false,id).stream().map(this::mapLinkDto).collect(Collectors.toList());
    }

    private LinkDto mapLinkDto(Link link){
        return LinkDto.builder().linkName(link.getLinkName()).linkId(link.getLinkId()).archived(link.isArchived()).subjects_name(link.getSubjects().getSubjectName()).subjects(link.getSubjects().getSubjectId()).url(link.getUrl()).created(link.getCreated()).build();
    }

    private Link mapPostDto(LinkDto linkDto) {
        Subjects subject = subjectsRepository.findById(linkDto.getSubjects()) .orElseThrow(() -> new SpringRedditException("Subject not found"));

        return Link.builder().linkName(linkDto.getLinkName())
                .url(linkDto.getUrl()).subjects(subject).created(Instant.now()).archived(false).build();
    }


}