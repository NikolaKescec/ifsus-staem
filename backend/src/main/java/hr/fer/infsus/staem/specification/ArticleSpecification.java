package hr.fer.infsus.staem.specification;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.Article_;
import hr.fer.infsus.staem.entity.Category;
import hr.fer.infsus.staem.entity.Developer;
import hr.fer.infsus.staem.entity.Genre;
import hr.fer.infsus.staem.entity.Publisher;
import hr.fer.infsus.staem.repository.query.FindArticleQuery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class ArticleSpecification {

    private ArticleSpecification() {
        throw new UnsupportedOperationException("Specification class!");
    }

    public static Specification<Article> create(FindArticleQuery findArticleQuery) {
        return (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (findArticleQuery.getTitle() != null) {
                predicates.add(cb.like(cb.lower(root.get("title")),
                    "%" + findArticleQuery.getTitle().toLowerCase(Locale.ROOT) + "%"));
            }

            if (findArticleQuery.getCategoryId() != null) {
                predicates.add(ArticleSpecification.<Category>byJoinedId(root, cb, Article_.CATEGORIES,
                    findArticleQuery.getCategoryId()));
            }

            if (findArticleQuery.getGenreId() != null) {
                predicates.add(
                    ArticleSpecification.<Genre>byJoinedId(root, cb, Article_.GENRES, findArticleQuery.getGenreId()));
            }

            if (findArticleQuery.getDeveloperId() != null) {
                predicates.add(ArticleSpecification.<Developer>byJoinedId(root, cb, Article_.DEVELOPERS,
                    findArticleQuery.getDeveloperId()));
            }

            if (findArticleQuery.getPublisherId() != null) {
                predicates.add(ArticleSpecification.<Publisher>byJoinedId(root, cb, Article_.PUBLISHERS,
                    findArticleQuery.getPublisherId()));
            }

            if (findArticleQuery.getPriceRange() != null) {
                predicates.add(cb.between(root.get(Article_.PRICE), findArticleQuery.getPriceRange().getMinPrice(),
                    findArticleQuery.getPriceRange().getMaxPrice()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }

    private static <T> Predicate byJoinedId(Root<Article> root, CriteriaBuilder cb, String joinTarget, Long id) {
        final Join<Article, T> leftOuterJoin = root.join(joinTarget, JoinType.LEFT);
        return cb.equal(leftOuterJoin.get("id"), id);
    }

}
