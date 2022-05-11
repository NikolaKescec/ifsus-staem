package hr.fer.infsus.staem.controller.request.update;

import hr.fer.infsus.staem.controller.request.shared.PictureRequest;
import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.repository.ArticleRepository;
import hr.fer.infsus.staem.repository.CategoryRepository;
import hr.fer.infsus.staem.repository.DeveloperRepository;
import hr.fer.infsus.staem.repository.GenreRepository;
import hr.fer.infsus.staem.repository.PublisherRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import hr.fer.infsus.staem.validator.validdlc.ValidDlc;
import hr.fer.infsus.staem.validator.validdlc.ValidDlcFields;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ValidDlc
public class UpdateArticleRequest implements ValidDlcFields {

    @EntityExists(repository = ArticleRepository.class, message = "Article with id {value} does not exist")
    private Long id;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Currency is required")
    @Size(min = 3, max = 3, message = "Currency must be 3 characters long")
    private String currency;

    @NotEmpty(message = "Picture url is required")
    private String pictureUrl;

    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;

    @NotNull(message = "Article type is required")
    private ArticleType articleType;

    private Long baseArticleId;

    private List<@EntityExists(repository = PublisherRepository.class, message = "Entity does not exist!") Long>
        publishers = new ArrayList<>();

    private List<@EntityExists(repository = DeveloperRepository.class, message = "Entity does not exist!") Long>
        developers = new ArrayList<>();

    private List<@EntityExists(repository = CategoryRepository.class, message = "Entity does not exist!") Long>
        categories = new ArrayList<>();

    private List<@EntityExists(repository = GenreRepository.class, message = "Entity does not exist!") Long> genres =
        new ArrayList<>();

    private List<@Valid PictureRequest> pictures = new ArrayList<>();

}
