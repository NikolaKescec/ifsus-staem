package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.PurchasedArticles;
import hr.fer.infsus.staem.repository.PurchasedArticlesRepository;
import hr.fer.infsus.staem.service.PurchasedArticleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchasedArticleQueryServiceImpl implements PurchasedArticleQueryService {

    private final PurchasedArticlesRepository purchasedArticlesRepository;

    @Override
    public boolean alreadyBoughtAnyItem(String subject, List<Long> articlesId) {
        return purchasedArticlesRepository.existsByUser_IdAndArticle_IdIn(subject, articlesId);
    }

    @Override
    public boolean alreadyBought(String subject, Article article) {
        return purchasedArticlesRepository.existsByUser_IdAndArticle_IdIn(subject,
            Collections.singletonList(article.getId()));
    }

    @Override
    public List<PurchasedArticles> findBought(String user) {
        return purchasedArticlesRepository.findAllByUser_IdOrderByDateOfPurchaseAsc(user);
    }

}
