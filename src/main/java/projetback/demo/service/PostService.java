package projetback.demo.service;

import projetback.demo.dto.PostDto;
import projetback.demo.exceptions.SpringRedditException;
import projetback.demo.model.Post;
import projetback.demo.model.Subjects;
import projetback.demo.repository.PostRepository;
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
public class PostService {
    private final PostRepository postRepository;
    private final SubjectsRepository subjectsRepository;

    @Transactional
    public PostDto save(PostDto postDto){
        Post post = mapPostDto(postDto);
        Post post1= postRepository.save(post);
        postDto.setPostId(post1.getPostId());
        postDto.setCreated(post1.getCreated());

        postDto.setSubjects(post1.getSubjects().getSubjectId());
        return postDto;
    }
    public Post ArchivePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new SpringRedditException("Post not found"));
        post.setArchived(true);
        return postRepository.save(post);
    }


    public PostDto update(PostDto postDto) {
        Post post = postRepository.findById(postDto.getPostId()).orElseThrow(()->new SpringRedditException("Post not found"));
        post.setPostName(postDto.getPostName());
        post.setCreated(Instant.now());
        post.setContent(postDto.getContent());
        Subjects subjects=subjectsRepository.findById(postDto.getSubjects()).orElseThrow(()->new SpringRedditException("Subject not found"));
        post.setSubjects(subjects);
        postRepository.save(post);
        return postDto;
    }

    @Transactional(readOnly = true)
    public List<PostDto> getAll(Long id) {
        return postRepository.findsalim(false,id).stream().map(this::mapPostDto).collect(Collectors.toList());
    }

    private PostDto mapPostDto(Post post){


        return PostDto.builder().postName(post.getPostName()).subjects(post.getSubjects().getSubjectId()).postId(post.getPostId()).content(post.getContent()).created(post.getCreated()).build();
    }

    private Post mapPostDto(PostDto postDto) {
        Subjects subject = subjectsRepository.findById(postDto.getSubjects()) .orElseThrow(() -> new SpringRedditException("Subject not found"));

        return Post.builder().postName(postDto.getPostName()).archived(false)
                .content(postDto.getContent()).subjects(subject).created(Instant.now()).build();
    }

    public PostDto getpost(Long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new SpringRedditException("post not found"));
        PostDto postDto=new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setPostName(post.getPostName());
        postDto.setSubjects(post.getSubjects().getSubjectId());
        postDto.setCreated(post.getCreated());
        postDto.setContent(post.getContent());
        return postDto;
    }
}

