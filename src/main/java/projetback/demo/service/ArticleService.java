package projetback.demo.service;


import projetback.demo.dto.ArticleDto;
import projetback.demo.exceptions.SpringRedditException;
import projetback.demo.model.Article;
import projetback.demo.model.Subjects;
import projetback.demo.repository.ArticleRepository;
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
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final SubjectsRepository subjectsRepository;

    @Transactional
    public ArticleDto save(ArticleDto articleDto){
        Article article = mapArticleDto(articleDto);
        Article article1= articleRepository.save(article);
        articleDto.setArticleId(article1.getArticleId());
        articleDto.setCreated(article1.getCreated());
        articleDto.setSubjects(article1.getSubjects().getSubjectId());
        articleDto.setContent(article1.getContent());
        return articleDto;
    }
    public Article ArchiveArticle(Long id){
        Article article=articleRepository.findById(id).orElseThrow(()->new SpringRedditException("Article not found"));
        article.setArchived(!article.isArchived());
        Article article1=articleRepository.save(article);
        return article1;
    }
    public ArticleDto update(ArticleDto articleDto) {
        Article article=articleRepository.findById(articleDto.getArticleId()).orElseThrow(()->new SpringRedditException("Article not found"));
        article.setArticleName(articleDto.getArticleName());
        article.setAttachment(articleDto.getAttachment());
        article.setCreated(Instant.now());
        article.setContent(articleDto.getContent());
        Subjects subjects = subjectsRepository.findById(articleDto.getSubjects()).orElseThrow(()->new SpringRedditException("Subject not found"));
        article.setSubjects(subjects);
        articleRepository.save(article);
        return articleDto;
    }

    @Transactional(readOnly = true)
    public List<ArticleDto> getAll(Long id) {

        List<Article> articles = articleRepository.findsalim(false,id);

        return articles.stream().map(this::mapArticleDto).collect(Collectors.toList());
    }

    private ArticleDto mapArticleDto(Article article){

        return ArticleDto.builder().articleName(article.getArticleName()).archived(article.isArchived()).subjects_name(article.getSubjects().getSubjectName()).subjects_name(article.getSubjects().getSubjectName()).subjects(article.getSubjects().getSubjectId()).articleId(article.getArticleId()).content(article.getContent()).attachment(article.getAttachment()).created(article.getCreated()).build();
    }

    private Article mapArticleDto(ArticleDto articleDto) {
        Subjects subject = subjectsRepository.findById(articleDto.getSubjects()) .orElseThrow(() -> new SpringRedditException("Subject not found"));
        return Article.builder().articleName(articleDto.getArticleName()).archived(false)
                .attachment(articleDto.getAttachment()).subjects(subject).content(articleDto.getContent()).
                        created(Instant.now()).build();
    }

    public ArticleDto getArticle(Long id) {
        Article article=articleRepository.findById(id).orElseThrow(()->new SpringRedditException("No article found"));
        ArticleDto articleDto =new ArticleDto();
        articleDto.setArticleId(article.getArticleId());
        articleDto.setArticleName(article.getArticleName());
        articleDto.setSubjects(article.getSubjects().getSubjectId());
        articleDto.setCreated(article.getCreated());
        articleDto.setAttachment(article.getAttachment());
        articleDto.setContent(article.getContent());
        articleDto.setSubjects_name(article.getSubjects().getSubjectName());
        articleDto.setArchived(article.isArchived());
        return articleDto;
    }


}
