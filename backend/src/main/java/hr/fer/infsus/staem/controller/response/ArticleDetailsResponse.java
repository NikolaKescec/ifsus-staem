package hr.fer.infsus.staem.controller.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ArticleDetailsResponse {

    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private String currency;

    private String pictureUrl;

    private LocalDate releaseDate;

    private String articleType;

    private Boolean alreadyBought;

    private List<ArticleResponse> dlcs;

    private List<PublisherResponse> publishers;

    private List<DeveloperResponse> developers;

    private List<CategoryResponse> categories;

    private List<GenreResponse> genres;

    private List<PictureResponse> pictures;

}
