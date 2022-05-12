package hr.fer.infsus.staem.mapper;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.Cart;
import hr.fer.infsus.staem.entity.Users;
import hr.fer.infsus.staem.mapper.core.BiCreateMapper;
import hr.fer.infsus.staem.service.ArticleQueryService;
import hr.fer.infsus.staem.service.command.create.CreateCartCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsersCreateCartCommandBiCreateMapper implements BiCreateMapper<Users, CreateCartCommand, Cart> {

    private final ArticleQueryService articleQueryService;

    @Override
    public Cart map(Users firstSource, CreateCartCommand secondSource) {
        final Cart cart = new Cart();

        final Set<Article> articles =
            secondSource.getArticles().stream().map(articleQueryService::findById).collect(Collectors.toSet());

        cart.setArticles(articles);
        cart.setPurchaseDate(LocalDate.now());
        cart.setUser(firstSource);

        return cart;
    }

}
