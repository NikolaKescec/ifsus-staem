package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.entity.Publisher;

public class PublisherTestBuilder {

    private final Publisher publisher;

    private PublisherTestBuilder() {
        this.publisher = new Publisher();
    }

    public Publisher build() {
        return this.publisher;
    }

    public static PublisherTestBuilder builder() {
        return new PublisherTestBuilder();
    }

    public PublisherTestBuilder def() {
        this.publisher.setId(1L);
        this.publisher.setName("publisher");

        return this;
    }

    public PublisherTestBuilder withId(Long id) {
        this.publisher.setId(id);

        return this;
    }

    public PublisherTestBuilder withName(String name) {
        this.publisher.setName(name);

        return this;
    }

}
