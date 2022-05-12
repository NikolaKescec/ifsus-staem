package hr.fer.infsus.staem.mapper;

import hr.fer.infsus.staem.controller.response.ArticleDetailsResponse;
import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.mapper.core.BiCreateMapper;
import hr.fer.infsus.staem.mapper.core.GenericCreateMapper;
import hr.fer.infsus.staem.service.PurchasedArticleQueryService;
import hr.fer.infsus.staem.service.UsersQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleSubjectArticleDetailsResponseBiCreateMapper
    implements BiCreateMapper<Article, String, ArticleDetailsResponse> {

    private final GenericCreateMapper genericCreateMapper;

    private final PurchasedArticleQueryService purchasedArticleQueryService;

    private final UsersQueryService usersQueryService;

    @Override
    public ArticleDetailsResponse map(Article article, String subject) {
        final ArticleDetailsResponse articleDetailsResponse =
            genericCreateMapper.map(article, ArticleDetailsResponse.class);

        if (subject != null) {
            articleDetailsResponse.setAlreadyBought(purchasedArticleQueryService.alreadyBought(subject, article));
        } else {
            articleDetailsResponse.setAlreadyBought(false);
        }

        return articleDetailsResponse;
    }

}
