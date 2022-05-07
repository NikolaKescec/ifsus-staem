package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.mapper.CreateMapper;
import hr.fer.infsus.staem.repository.ArticleRepository;
import hr.fer.infsus.staem.service.ArticleCommandService;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    private final CreateMapper<CreateArticleCommand, Article> createArticleCommandArticleCreateMapper;

    //TODO: ZAMIJENITI SA SEQUENCE JER OVO NIJE TOCNO (ali radi)
    @Transactional
    @Override
    public Article create(CreateArticleCommand createArticleCommand) {
        final Article article = createArticleCommandArticleCreateMapper.map(createArticleCommand);

        final List<Article> dlcs = article.getDlcs();
        article.setDlcs(Collections.emptyList());

        final Article noDlcArticle = articleRepository.save(article);

        long dlcId = noDlcArticle.getId() + 1;
        for (Article dlc : dlcs) {
            dlc.setId(dlcId);
            dlcId++;
        }

        final List<Article> savedDlcs = articleRepository.saveAll(dlcs);

        noDlcArticle.setDlcs(savedDlcs);
        return articleRepository.save(noDlcArticle);
    }

}
