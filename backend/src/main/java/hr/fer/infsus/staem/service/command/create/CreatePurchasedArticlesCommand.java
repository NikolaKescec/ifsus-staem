package hr.fer.infsus.staem.service.command.create;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreatePurchasedArticlesCommand {

    private Users user;

    private Article article;

    private LocalDate dateOfPurchase;

    private BigDecimal price;

}
