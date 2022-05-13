package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.entity.Developer;

public class DeveloperTestBuilder {

    private final Developer developer;

    private DeveloperTestBuilder() {
        this.developer = new Developer();
    }

    public Developer build() {
        return this.developer;
    }

    public static DeveloperTestBuilder builder() {
        return new DeveloperTestBuilder();
    }

    public DeveloperTestBuilder def() {
        this.developer.setId(1L);
        this.developer.setName("developer");

        return this;
    }

    public DeveloperTestBuilder withId(Long id) {
        this.developer.setId(id);

        return this;
    }

    public DeveloperTestBuilder withName(String name) {
        this.developer.setName(name);

        return this;
    }

}
