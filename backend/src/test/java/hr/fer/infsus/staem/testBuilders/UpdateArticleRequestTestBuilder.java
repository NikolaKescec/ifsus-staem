package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.controller.request.shared.PictureRequest;
import hr.fer.infsus.staem.controller.request.update.UpdateArticleRequest;
import hr.fer.infsus.staem.entity.ArticleType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class UpdateArticleRequestTestBuilder {

    private final UpdateArticleRequest updateArticleRequest;

    private UpdateArticleRequestTestBuilder() {
        this.updateArticleRequest = new UpdateArticleRequest();
    }

    public UpdateArticleRequest build() {
        return this.updateArticleRequest;
    }

    public static UpdateArticleRequestTestBuilder builder() {
        return new UpdateArticleRequestTestBuilder();
    }

    public UpdateArticleRequestTestBuilder def() {
        this.updateArticleRequest.setTitle("title");
        this.updateArticleRequest.setPrice(BigDecimal.ONE);
        this.updateArticleRequest.setPictures(Collections.emptyList());
        this.updateArticleRequest.setType(ArticleType.GAME);
        this.updateArticleRequest.setBaseArticleId(null);
        this.updateArticleRequest.setCategories(List.of(1L));
        this.updateArticleRequest.setPublishers(List.of(1L));
        this.updateArticleRequest.setDevelopers(List.of(1L));
        this.updateArticleRequest.setGenres(List.of(1L));
        this.updateArticleRequest.setPictureUrl("pictureUrl");
        this.updateArticleRequest.setCurrency("USD");
        this.updateArticleRequest.setReleaseDate(LocalDate.of(2018, 1, 1));
        this.updateArticleRequest.setDescription("description");

        return this;
    }

    public UpdateArticleRequestTestBuilder withTitle(String title) {
        this.updateArticleRequest.setTitle(title);

        return this;
    }

    public UpdateArticleRequestTestBuilder withArticleType(ArticleType createArticleCommandType) {
        this.updateArticleRequest.setType(createArticleCommandType);

        return this;
    }

    public UpdateArticleRequestTestBuilder withPrice(BigDecimal price) {
        this.updateArticleRequest.setPrice(price);

        return this;
    }

    public UpdateArticleRequestTestBuilder withPictures(List<PictureRequest> pictures) {
        this.updateArticleRequest.setPictures(pictures);

        return this;
    }

    public UpdateArticleRequestTestBuilder withBaseArticleId(Long baseArticleId) {
        this.updateArticleRequest.setBaseArticleId(baseArticleId);

        return this;
    }

    public UpdateArticleRequestTestBuilder withCategories(List<Long> categories) {
        this.updateArticleRequest.setCategories(categories);

        return this;
    }

    public UpdateArticleRequestTestBuilder withDevelopers(List<Long> developers) {
        this.updateArticleRequest.setDevelopers(developers);

        return this;
    }

    public UpdateArticleRequestTestBuilder withGenres(List<Long> genres) {
        this.updateArticleRequest.setGenres(genres);

        return this;
    }

    public UpdateArticleRequestTestBuilder withPublishers(List<Long> publishers) {
        this.updateArticleRequest.setPublishers(publishers);

        return this;
    }

}
