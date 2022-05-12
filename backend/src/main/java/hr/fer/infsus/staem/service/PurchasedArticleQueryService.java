package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.PurchasedArticles;

import java.util.List;

public interface PurchasedArticleQueryService {

    boolean alreadyBoughtAnyItem(String user, List<Long> articles);

    boolean alreadyBought(String user, Article article);

    List<PurchasedArticles> findBought(String user);

}
