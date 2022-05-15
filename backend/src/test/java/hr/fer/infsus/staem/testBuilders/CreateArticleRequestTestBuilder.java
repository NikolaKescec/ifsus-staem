package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.controller.request.create.CreateArticleRequest;
import hr.fer.infsus.staem.controller.request.shared.PictureRequest;
import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.service.command.shared.PictureCommand;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CreateArticleRequestTestBuilder {

    private final CreateArticleRequest createArticleRequest;

    private CreateArticleRequestTestBuilder() {
        this.createArticleRequest = new CreateArticleRequest();
    }

    public CreateArticleRequest build() {
        return this.createArticleRequest;
    }

    public static CreateArticleRequestTestBuilder builder() {
        return new CreateArticleRequestTestBuilder();
    }

    public CreateArticleRequestTestBuilder def() {
        this.createArticleRequest.setTitle("title");
        this.createArticleRequest.setPrice(BigDecimal.ONE);
        this.createArticleRequest.setPictures(Collections.emptyList());
        this.createArticleRequest.setType(ArticleType.GAME);
        this.createArticleRequest.setBaseArticleId(null);
        this.createArticleRequest.setCategories(List.of(1L));
        this.createArticleRequest.setPublishers(List.of(1L));
        this.createArticleRequest.setDevelopers(List.of(1L));
        this.createArticleRequest.setGenres(List.of(1L));
        this.createArticleRequest.setPictureUrl("pictureUrl");
        this.createArticleRequest.setCurrency("USD");
        this.createArticleRequest.setReleaseDate(LocalDate.of(2018, 1, 1));
        this.createArticleRequest.setDescription("description");

        return this;
    }

    public CreateArticleRequestTestBuilder withTitle(String title) {
        this.createArticleRequest.setTitle(title);

        return this;
    }

    public CreateArticleRequestTestBuilder withArticleType(ArticleType createArticleCommandType) {
        this.createArticleRequest.setType(createArticleCommandType);

        return this;
    }

    public CreateArticleRequestTestBuilder withPrice(BigDecimal price) {
        this.createArticleRequest.setPrice(price);

        return this;
    }

    public CreateArticleRequestTestBuilder withPictures(List<PictureRequest> pictures) {
        this.createArticleRequest.setPictures(pictures);

        return this;
    }

    public CreateArticleRequestTestBuilder withBaseArticleId(Long baseArticleId) {
        this.createArticleRequest.setBaseArticleId(baseArticleId);

        return this;
    }

    public CreateArticleRequestTestBuilder withCategories(List<Long> categories) {
        this.createArticleRequest.setCategories(categories);

        return this;
    }

    public CreateArticleRequestTestBuilder withDevelopers(List<Long> developers) {
        this.createArticleRequest.setDevelopers(developers);

        return this;
    }

    public CreateArticleRequestTestBuilder withGenres(List<Long> genres) {
        this.createArticleRequest.setGenres(genres);

        return this;
    }

    public CreateArticleRequestTestBuilder withPublishers(List<Long> publishers) {
        this.createArticleRequest.setPublishers(publishers);

        return this;
    }

}
