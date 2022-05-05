package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.exception.EntityNotFoundException;
import hr.fer.infsus.staem.repository.ArticleRepository;
import hr.fer.infsus.staem.repository.query.FindArticleQuery;
import hr.fer.infsus.staem.service.ArticleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public Page<Article> findByQuery(FindArticleQuery findArticleQuery) {
        return articleRepository.findByQuery(findArticleQuery);
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Article.class, new String[] { "id" }));
    }

}
