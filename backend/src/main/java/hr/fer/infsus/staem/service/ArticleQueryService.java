package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.repository.query.FindArticleQuery;
import org.springframework.data.domain.Page;

public interface ArticleQueryService {

    Page<Article> findByQuery(FindArticleQuery findArticleQuery);

    Article findById(Long id);

}
