package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Cart;
import hr.fer.infsus.staem.entity.Users;
import hr.fer.infsus.staem.exception.ArticleAlreadyBought;
import hr.fer.infsus.staem.mapper.core.CreateMapper;
import hr.fer.infsus.staem.service.CartCommandService;
import hr.fer.infsus.staem.service.CartSagaService;
import hr.fer.infsus.staem.service.PurchasedArticleCommandService;
import hr.fer.infsus.staem.service.PurchasedArticleQueryService;
import hr.fer.infsus.staem.service.UsersCommandService;
import hr.fer.infsus.staem.service.UsersQueryService;
import hr.fer.infsus.staem.service.command.create.CreateCartCommand;
import hr.fer.infsus.staem.service.command.create.CreatePurchasedArticlesCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartSagaServiceImpl implements CartSagaService {

    private final UsersCommandService usersCommandService;

    private final UsersQueryService usersQueryService;

    private final CartCommandService cartCommandService;

    private final PurchasedArticleQueryService purchasedArticleQueryService;

    private final PurchasedArticleCommandService purchasedArticleCommandService;

    private final CreateMapper<Cart, List<CreatePurchasedArticlesCommand>> cartListCreateMapper;

    @Override
    public void create(String currentSubject, CreateCartCommand createCartCommand) {
        Users currentUser = usersQueryService.findById(currentSubject);
        if (currentUser == null) {
            currentUser = usersCommandService.create(currentSubject);
        } else if (purchasedArticleQueryService.alreadyBoughtAnyItem(currentUser.getId(),
            createCartCommand.getArticles())) {
            throw new ArticleAlreadyBought();
        }

        final Cart savedCart = cartCommandService.create(currentUser, createCartCommand);

        final List<CreatePurchasedArticlesCommand> createPurchasedArticlesCommandList =
            cartListCreateMapper.map(savedCart);

        purchasedArticleCommandService.createAll(createPurchasedArticlesCommandList);
    }

}
