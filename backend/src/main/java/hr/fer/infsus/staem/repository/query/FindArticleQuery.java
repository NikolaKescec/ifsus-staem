package hr.fer.infsus.staem.repository.query;

import lombok.Data;

import javax.validation.Valid;

@Data
public class FindArticleQuery {

    private String title;

    private Long publisherId;

    private Long developerId;

    private Long categoryId;

    private Long genreId;

    @Valid
    private PriceRange priceRange;

    @Valid
    private PageInfo pageInfo = new PageInfo();

}
