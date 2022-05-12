package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.mapper.CreateMapper;
import hr.fer.infsus.staem.mapper.UpdateMapper;
import hr.fer.infsus.staem.repository.ArticleRepository;
import hr.fer.infsus.staem.service.ArticleCommandService;
import hr.fer.infsus.staem.service.ArticleQueryService;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;
import hr.fer.infsus.staem.service.command.update.UpdateArticleCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    private final ArticleQueryService articleQueryService;

    private final CreateMapper<CreateArticleCommand, Article> createArticleCommandArticleCreateMapper;

    private final UpdateMapper<UpdateArticleCommand, Article> updateArticleCommandArticleCreateMapper;

    @Transactional
    @Override
    public Article create(CreateArticleCommand createArticleCommand) {
        final Article article = createArticleCommandArticleCreateMapper.map(createArticleCommand);

        return articleRepository.save(article);
    }

    @Override
    public Article update(UpdateArticleCommand updateArticleCommand) {
        final Article article = articleQueryService.findById(updateArticleCommand.getId());
        updateArticleCommandArticleCreateMapper.map(updateArticleCommand, article);

        return articleRepository.save(article);
    }

    @Override
    public void delete(Long id) {
        final Article article = articleQueryService.findById(id);

        if (article.getArticleType() == ArticleType.DLC) {
            final List<Article> dlcs = article.getBaseArticle().getDlcs();
            dlcs.remove(article);
            article.getBaseArticle().setDlcs(dlcs);
            article.setBaseArticle(null);
        }

        articleRepository.delete(article);
    }

}
