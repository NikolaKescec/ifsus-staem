package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.entity.Genre;

public class GenreTestBuilder {

    private final Genre genre;

    private GenreTestBuilder() {
        this.genre = new Genre();
    }

    public Genre build() {
        return this.genre;
    }

    public static GenreTestBuilder builder() {
        return new GenreTestBuilder();
    }

    public GenreTestBuilder def() {
        this.genre.setId(1L);
        this.genre.setName("genre");

        return this;
    }

    public GenreTestBuilder withId(Long id) {
        this.genre.setId(id);

        return this;
    }

    public GenreTestBuilder withName(String name) {
        this.genre.setName(name);

        return this;
    }

}
