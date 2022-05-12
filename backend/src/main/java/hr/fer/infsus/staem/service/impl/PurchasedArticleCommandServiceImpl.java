package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.PurchasedArticles;
import hr.fer.infsus.staem.mapper.core.GenericCreateMapper;
import hr.fer.infsus.staem.repository.PurchasedArticlesRepository;
import hr.fer.infsus.staem.service.PurchasedArticleCommandService;
import hr.fer.infsus.staem.service.command.create.CreatePurchasedArticlesCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PurchasedArticleCommandServiceImpl implements PurchasedArticleCommandService {

    private final PurchasedArticlesRepository purchasedArticlesRepository;

    private final GenericCreateMapper genericCreateMapper;

    @Override
    public List<PurchasedArticles> createAll(List<CreatePurchasedArticlesCommand> createPurchasedArticlesCommands) {
        final List<PurchasedArticles> purchasedArticles =
            genericCreateMapper.mapToList(createPurchasedArticlesCommands, PurchasedArticles.class);

        return purchasedArticlesRepository.saveAll(purchasedArticles);
    }

}
