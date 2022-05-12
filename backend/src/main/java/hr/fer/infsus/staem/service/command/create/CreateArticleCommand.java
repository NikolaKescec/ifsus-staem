package hr.fer.infsus.staem.service.command.create;

import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.service.command.shared.PictureCommand;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreateArticleCommand {

    private String title;

    private String description;

    private BigDecimal price;

    private String currency;

    private String pictureUrl;

    private LocalDate releaseDate;

    private ArticleType articleType;

    private Long baseArticleId;

    private List<Long> publishers;

    private List<Long> developers;

    private List<Long> categories;

    private List<Long> genres;

    private List<PictureCommand> pictures;

}
