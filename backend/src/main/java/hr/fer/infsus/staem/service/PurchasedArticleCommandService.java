package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.PurchasedArticles;
import hr.fer.infsus.staem.service.command.create.CreatePurchasedArticlesCommand;

import java.util.List;

public interface PurchasedArticleCommandService {

    List<PurchasedArticles> createAll(List<CreatePurchasedArticlesCommand> createPurchasedArticlesCommands);

}
