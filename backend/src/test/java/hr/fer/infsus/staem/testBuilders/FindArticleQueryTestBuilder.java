package hr.fer.infsus.staem.testBuilders;

import hr.fer.infsus.staem.repository.query.FindArticleQuery;
import hr.fer.infsus.staem.repository.query.PriceRange;

public class FindArticleQueryTestBuilder {

    private final FindArticleQuery findArticleQuery;

    private FindArticleQueryTestBuilder() {
        this.findArticleQuery = new FindArticleQuery();
    }

    public FindArticleQuery build() {
        return this.findArticleQuery;
    }

    public static FindArticleQueryTestBuilder builder() {
        return new FindArticleQueryTestBuilder();
    }

    public FindArticleQueryTestBuilder def() {
        final PriceRange priceRange = new PriceRange();
        priceRange.setMaxPrice(12.0);
        priceRange.setMinPrice(0.0);

        this.findArticleQuery.setTitle("");
        this.findArticleQuery.setCategoryId(null);
        this.findArticleQuery.setGenreId(null);
        this.findArticleQuery.setDeveloperId(null);
        this.findArticleQuery.setPublisherId(null);
        this.findArticleQuery.setPriceRange(priceRange);

        return this;
    }

    public FindArticleQueryTestBuilder withTitle(String title) {
        this.findArticleQuery.setTitle(title);

        return this;
    }

    public FindArticleQueryTestBuilder withCategoryId(Long categoryId) {
        this.findArticleQuery.setCategoryId(categoryId);

        return this;
    }

    public FindArticleQueryTestBuilder withGenreId(Long genreId) {
        this.findArticleQuery.setGenreId(genreId);

        return this;
    }

    public FindArticleQueryTestBuilder withDeveloperId(Long developerId) {
        this.findArticleQuery.setDeveloperId(developerId);

        return this;
    }

    public FindArticleQueryTestBuilder withPublisherId(Long publisherId) {
        this.findArticleQuery.setPublisherId(publisherId);

        return this;
    }

    public FindArticleQueryTestBuilder withPriceRange(PriceRange priceRange){
        this.findArticleQuery.setPriceRange(priceRange);

        return this;
    }



}
