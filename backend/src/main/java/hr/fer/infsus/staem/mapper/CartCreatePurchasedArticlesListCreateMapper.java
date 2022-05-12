package hr.fer.infsus.staem.mapper;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.Cart;
import hr.fer.infsus.staem.mapper.core.CreateMapper;
import hr.fer.infsus.staem.service.command.create.CreatePurchasedArticlesCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartCreatePurchasedArticlesListCreateMapper
    implements CreateMapper<Cart, List<CreatePurchasedArticlesCommand>> {

    @Override
    public List<CreatePurchasedArticlesCommand> map(Cart source) {
        final List<CreatePurchasedArticlesCommand> commands = new ArrayList<>();

        for (Article article : source.getArticles()) {
            commands.add(new CreatePurchasedArticlesCommand(source.getUser(), article, source.getPurchaseDate(),
                article.getPrice()));
        }

        return commands;
    }

}
