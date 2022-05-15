package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.service.command.shared.PictureCommand;
import hr.fer.infsus.staem.service.command.update.UpdateArticleCommand;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class UpdateArticleCommandTestBuilder {

    private final UpdateArticleCommand updateArticleCommand;

    private UpdateArticleCommandTestBuilder() {
        this.updateArticleCommand = new UpdateArticleCommand();
    }

    public UpdateArticleCommand build() {
        return this.updateArticleCommand;
    }

    public static UpdateArticleCommandTestBuilder builder() {
        return new UpdateArticleCommandTestBuilder();
    }

    public UpdateArticleCommandTestBuilder def() {
        this.updateArticleCommand.setId(1L);
        this.updateArticleCommand.setTitle("title");
        this.updateArticleCommand.setPrice(BigDecimal.ONE);
        this.updateArticleCommand.setPictures(Collections.emptyList());
        this.updateArticleCommand.setArticleType(ArticleType.GAME);
        this.updateArticleCommand.setBaseArticleId(null);
        this.updateArticleCommand.setCategories(List.of(1L));
        this.updateArticleCommand.setPublishers(List.of(1L));
        this.updateArticleCommand.setDevelopers(List.of(1L));
        this.updateArticleCommand.setGenres(List.of(1L));
        this.updateArticleCommand.setPictureUrl("pictureUrl");
        this.updateArticleCommand.setCurrency("USD");
        this.updateArticleCommand.setReleaseDate(LocalDate.of(2018, 1, 1));
        this.updateArticleCommand.setDescription("description");

        return this;
    }

    public UpdateArticleCommandTestBuilder withId(Long id) {
        this.updateArticleCommand.setId(id);

        return this;
    }

    public UpdateArticleCommandTestBuilder withTitle(String title) {
        this.updateArticleCommand.setTitle(title);

        return this;
    }

    public UpdateArticleCommandTestBuilder withArticleType(ArticleType createArticleCommandType) {
        this.updateArticleCommand.setArticleType(createArticleCommandType);

        return this;
    }

    public UpdateArticleCommandTestBuilder withPrice(BigDecimal price) {
        this.updateArticleCommand.setPrice(price);

        return this;
    }

    public UpdateArticleCommandTestBuilder withPictures(List<PictureCommand> pictures) {
        this.updateArticleCommand.setPictures(pictures);

        return this;
    }

    public UpdateArticleCommandTestBuilder withBaseArticleId(Long baseArticleId) {
        this.updateArticleCommand.setBaseArticleId(baseArticleId);

        return this;
    }

    public UpdateArticleCommandTestBuilder withCategories(List<Long> categories) {
        this.updateArticleCommand.setCategories(categories);

        return this;
    }

    public UpdateArticleCommandTestBuilder withDevelopers(List<Long> developers) {
        this.updateArticleCommand.setDevelopers(developers);

        return this;
    }

    public UpdateArticleCommandTestBuilder withGenres(List<Long> genres) {
        this.updateArticleCommand.setGenres(genres);

        return this;
    }

    public UpdateArticleCommandTestBuilder withPublishers(List<Long> publishers) {
        this.updateArticleCommand.setPublishers(publishers);

        return this;
    }

}
