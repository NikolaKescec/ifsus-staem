package hr.fer.infsus.staem.repository;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.repository.query.FindArticleQuery;
import hr.fer.infsus.staem.specification.ArticleSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    default Page<Article> findByQuery(FindArticleQuery findArticleQuery) {
        return findAll(ArticleSpecification.create(findArticleQuery),
            PageRequest.of(findArticleQuery.getPageInfo().getPage(), findArticleQuery.getPageInfo().getSize()));
    }

}