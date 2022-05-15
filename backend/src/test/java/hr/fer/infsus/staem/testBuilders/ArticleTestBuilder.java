package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.entity.Category;
import hr.fer.infsus.staem.entity.Developer;
import hr.fer.infsus.staem.entity.Genre;
import hr.fer.infsus.staem.entity.Picture;
import hr.fer.infsus.staem.entity.Publisher;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;
import hr.fer.infsus.staem.service.command.update.UpdateArticleCommand;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleTestBuilder {

    private final Article article;

    private ArticleTestBuilder() {
        this.article = new Article();
    }

    public Article build() {
        return this.article;
    }

    public static ArticleTestBuilder builder() {
        return new ArticleTestBuilder();
    }

    public ArticleTestBuilder def() {
        this.article.setId(1L);
        this.article.setTitle("title");
        this.article.setArticleType(ArticleType.GAME);
        this.article.setPrice(BigDecimal.ONE);
        this.article.setPictures(Collections.emptyList());
        this.article.setDlcs(Collections.emptyList());
        this.article.setCategories(Set.of(CategoryTestBuilder.builder().def().build()));
        this.article.setPublishers(Set.of(PublisherTestBuilder.builder().def().build()));
        this.article.setDevelopers(Set.of(DeveloperTestBuilder.builder().def().build()));
        this.article.setGenres(Set.of(GenreTestBuilder.builder().def().build()));

        return this;
    }

    public ArticleTestBuilder fromCommand(CreateArticleCommand createArticleCommand) {
        this.article.setTitle(createArticleCommand.getTitle());
        this.article.setArticleType(createArticleCommand.getArticleType());
        this.article.setPrice(createArticleCommand.getPrice());
        this.article.setPictures(List.of(new Picture()));
        if (createArticleCommand.getArticleType() == ArticleType.GAME) {
            this.article.setDlcs(List.of(new Article()));
            this.article.setBaseArticle(null);
        } else {
            this.article.setDlcs(Collections.emptyList());
            this.article.setBaseArticle(
                ArticleTestBuilder.builder()
                    .def()
                    .withId(createArticleCommand.getBaseArticleId())
                    .withDlcs(Collections.singletonList(
                        ArticleTestBuilder.builder().withArticleType(ArticleType.DLC)
                            .withId(createArticleCommand.getBaseArticleId() + 1).build()))
                    .build());
        }
        this.article.setCategories(createArticleCommand.getCategories().stream()
            .map(aLong -> CategoryTestBuilder.builder().def().withId(aLong).build()).collect(
                Collectors.toSet()));
        this.article.setDevelopers(createArticleCommand.getCategories().stream()
            .map(aLong -> DeveloperTestBuilder.builder().def().withId(aLong).build()).collect(
                Collectors.toSet()));
        this.article.setPublishers(createArticleCommand.getCategories().stream()
            .map(aLong -> PublisherTestBuilder.builder().def().withId(aLong).build()).collect(
                Collectors.toSet()));
        this.article.setGenres(createArticleCommand.getCategories().stream()
            .map(aLong -> GenreTestBuilder.builder().def().withId(aLong).build()).collect(
                Collectors.toSet()));

        return this;
    }

    public ArticleTestBuilder fromCommand(UpdateArticleCommand updateArticleCommand) {
        this.article.setTitle(updateArticleCommand.getTitle());
        this.article.setArticleType(updateArticleCommand.getArticleType());
        this.article.setPrice(updateArticleCommand.getPrice());
        this.article.setPictures(List.of(new Picture()));
        if (updateArticleCommand.getArticleType() == ArticleType.GAME) {
            this.article.setDlcs(List.of(new Article()));
            this.article.setBaseArticle(null);
        } else {
            this.article.setDlcs(Collections.emptyList());
            this.article.setBaseArticle(
                ArticleTestBuilder.builder()
                    .def()
                    .withId(updateArticleCommand.getBaseArticleId())
                    .withDlcs(Collections.singletonList(
                        ArticleTestBuilder.builder().withArticleType(ArticleType.DLC)
                            .withId(updateArticleCommand.getBaseArticleId() + 1).build()))
                    .build());
        }
        this.article.setCategories(updateArticleCommand.getCategories().stream()
            .map(aLong -> CategoryTestBuilder.builder().def().withId(aLong).build()).collect(
                Collectors.toSet()));
        this.article.setDevelopers(updateArticleCommand.getCategories().stream()
            .map(aLong -> DeveloperTestBuilder.builder().def().withId(aLong).build()).collect(
                Collectors.toSet()));
        this.article.setPublishers(updateArticleCommand.getCategories().stream()
            .map(aLong -> PublisherTestBuilder.builder().def().withId(aLong).build()).collect(
                Collectors.toSet()));
        this.article.setGenres(updateArticleCommand.getCategories().stream()
            .map(aLong -> GenreTestBuilder.builder().def().withId(aLong).build()).collect(
                Collectors.toSet()));

        return this;
    }

    public ArticleTestBuilder withId(Long id) {
        this.article.setId(id);

        return this;
    }

    public ArticleTestBuilder withTitle(String title) {
        this.article.setTitle(title);

        return this;
    }

    public ArticleTestBuilder withArticleType(ArticleType articleType) {
        this.article.setArticleType(articleType);

        return this;
    }

    public ArticleTestBuilder withPrice(BigDecimal price) {
        this.article.setPrice(price);

        return this;
    }

    public ArticleTestBuilder withPictures(List<Picture> pictures) {
        this.article.setPictures(pictures);

        return this;
    }

    public ArticleTestBuilder withDlcs(List<Article> dlcs) {
        this.article.setDlcs(dlcs);

        return this;
    }

    public ArticleTestBuilder withCategories(Set<Category> categories) {
        this.article.setCategories(categories);

        return this;
    }

    public ArticleTestBuilder withDevelopers(Set<Developer> developers) {
        this.article.setDevelopers(developers);

        return this;
    }

    public ArticleTestBuilder withGenres(Set<Genre> genres) {
        this.article.setGenres(genres);

        return this;
    }

    public ArticleTestBuilder withPublishers(Set<Publisher> publishers) {
        this.article.setPublishers(publishers);

        return this;
    }

}
