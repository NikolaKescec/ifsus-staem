package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;
import hr.fer.infsus.staem.service.command.shared.PictureCommand;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CreateArticleCommandTestBuilder {

    private final CreateArticleCommand createArticleCommand;

    private CreateArticleCommandTestBuilder() {
        this.createArticleCommand = new CreateArticleCommand();
    }

    public CreateArticleCommand build() {
        return this.createArticleCommand;
    }

    public static CreateArticleCommandTestBuilder builder() {
        return new CreateArticleCommandTestBuilder();
    }

    public CreateArticleCommandTestBuilder def() {
        this.createArticleCommand.setTitle("title");
        this.createArticleCommand.setPrice(BigDecimal.ONE);
        this.createArticleCommand.setPictures(Collections.emptyList());
        this.createArticleCommand.setArticleType(ArticleType.GAME);
        this.createArticleCommand.setBaseArticleId(null);
        this.createArticleCommand.setCategories(List.of(1L));
        this.createArticleCommand.setPublishers(List.of(1L));
        this.createArticleCommand.setDevelopers(List.of(1L));
        this.createArticleCommand.setGenres(List.of(1L));
        this.createArticleCommand.setPictureUrl("pictureUrl");
        this.createArticleCommand.setCurrency("USD");
        this.createArticleCommand.setReleaseDate(LocalDate.of(2018, 1, 1));
        this.createArticleCommand.setDescription("description");

        return this;
    }

    public CreateArticleCommandTestBuilder withTitle(String title) {
        this.createArticleCommand.setTitle(title);

        return this;
    }

    public CreateArticleCommandTestBuilder withArticleType(ArticleType createArticleCommandType) {
        this.createArticleCommand.setArticleType(createArticleCommandType);

        return this;
    }

    public CreateArticleCommandTestBuilder withPrice(BigDecimal price) {
        this.createArticleCommand.setPrice(price);

        return this;
    }

    public CreateArticleCommandTestBuilder withPictures(List<PictureCommand> pictures) {
        this.createArticleCommand.setPictures(pictures);

        return this;
    }

    public CreateArticleCommandTestBuilder withBaseArticleId(Long baseArticleId) {
        this.createArticleCommand.setBaseArticleId(baseArticleId);

        return this;
    }

    public CreateArticleCommandTestBuilder withCategories(List<Long> categories) {
        this.createArticleCommand.setCategories(categories);

        return this;
    }

    public CreateArticleCommandTestBuilder withDevelopers(List<Long> developers) {
        this.createArticleCommand.setDevelopers(developers);

        return this;
    }

    public CreateArticleCommandTestBuilder withGenres(List<Long> genres) {
        this.createArticleCommand.setGenres(genres);

        return this;
    }

    public CreateArticleCommandTestBuilder withPublishers(List<Long> publishers) {
        this.createArticleCommand.setPublishers(publishers);

        return this;
    }

}
