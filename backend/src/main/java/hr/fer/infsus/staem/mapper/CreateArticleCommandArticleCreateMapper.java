package hr.fer.infsus.staem.mapper;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.service.ArticleQueryService;
import hr.fer.infsus.staem.service.CategoryQueryService;
import hr.fer.infsus.staem.service.DeveloperQueryService;
import hr.fer.infsus.staem.service.GenreQueryService;
import hr.fer.infsus.staem.service.PublisherQueryService;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CreateArticleCommandArticleCreateMapper implements CreateMapper<CreateArticleCommand, Article> {

    private final GenericCreateMapper genericCreateMapper;

    private final ArticleQueryService articleQueryService;

    private final PublisherQueryService publisherQueryService;

    private final GenreQueryService genreQueryService;

    private final CategoryQueryService categoryQueryService;

    private final DeveloperQueryService developerQueryService;

    @Override
    public Article map(CreateArticleCommand source) {
        final Article article = genericCreateMapper.map(source, Article.class);

        if (source.getArticleType() == ArticleType.DLC) {
            final List<Article> dlcs = article.getDlcs();
            dlcs.add(articleQueryService.findById(source.getBaseArticleId()));
            article.setDlcs(dlcs);
        }

        for (Long publisherId : source.getPublishers()) {
            article.addPublisher(publisherQueryService.findById(publisherId));
        }

        for (Long genreId : source.getGenres()) {
            article.addGenre(genreQueryService.findById(genreId));
        }

        for (Long categoryId : source.getCategories()) {
            article.addCategory(categoryQueryService.findById(categoryId));
        }

        for (Long developerId : source.getDevelopers()) {
            article.addDeveloper(developerQueryService.findById(developerId));
        }

        return article;
    }

}
