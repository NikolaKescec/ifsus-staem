package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.entity.Category;

public class CategoryTestBuilder {

    private final Category category;

    private CategoryTestBuilder() {
        this.category = new Category();
    }

    public Category build() {
        return this.category;
    }

    public static CategoryTestBuilder builder() {
        return new CategoryTestBuilder();
    }

    public CategoryTestBuilder def() {
        this.category.setId(1L);
        this.category.setName("category");

        return this;
    }

    public CategoryTestBuilder withId(Long id) {
        this.category.setId(id);

        return this;
    }

    public CategoryTestBuilder withName(String name) {
        this.category.setName(name);

        return this;
    }

}
