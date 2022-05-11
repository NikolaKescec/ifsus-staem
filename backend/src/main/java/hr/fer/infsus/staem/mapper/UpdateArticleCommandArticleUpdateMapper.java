package hr.fer.infsus.staem.mapper;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.ArticleType;
import hr.fer.infsus.staem.entity.Category;
import hr.fer.infsus.staem.entity.Developer;
import hr.fer.infsus.staem.entity.Genre;
import hr.fer.infsus.staem.entity.Publisher;
import hr.fer.infsus.staem.service.ArticleQueryService;
import hr.fer.infsus.staem.service.CategoryQueryService;
import hr.fer.infsus.staem.service.DeveloperQueryService;
import hr.fer.infsus.staem.service.GenreQueryService;
import hr.fer.infsus.staem.service.PublisherQueryService;
import hr.fer.infsus.staem.service.command.update.UpdateArticleCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class UpdateArticleCommandArticleUpdateMapper implements UpdateMapper<UpdateArticleCommand, Article> {

    private final GenericUpdateMapper genericUpdateMapper;

    private final ArticleQueryService articleQueryService;

    private final PublisherQueryService publisherQueryService;

    private final GenreQueryService genreQueryService;

    private final CategoryQueryService categoryQueryService;

    private final DeveloperQueryService developerQueryService;

    @Override
    public void map(UpdateArticleCommand source, Article article) {
        genericUpdateMapper.map(source, article);

        for (Publisher publisher : article.getPublishers()) {
            publisher.removeArticle(article);
        }
        final Set<Publisher> updatedPublishers = new HashSet<>();
        for (Long publisherId : source.getPublishers()) {
            updatedPublishers.add(publisherQueryService.findById(publisherId));
        }
        article.setPublishers(updatedPublishers);

        for (Genre genre : article.getGenres()) {
            genre.removeArticle(article);
        }
        final Set<Genre> updatedGenre = new HashSet<>();
        for (Long genreId : source.getGenres()) {
            updatedGenre.add(genreQueryService.findById(genreId));
        }
        article.setGenres(updatedGenre);

        for (Category category : article.getCategories()) {
            category.removeArticle(article);
        }
        final Set<Category> updatedCategory = new HashSet<>();
        for (Long categoryId : source.getGenres()) {
            updatedCategory.add(categoryQueryService.findById(categoryId));
        }
        article.setCategories(updatedCategory);

        for (Developer developer : article.getDevelopers()) {
            developer.removeArticle(article);
        }
        final Set<Developer> updatedDevelopers = new HashSet<>();
        for (Long developerId : source.getDevelopers()) {
            updatedDevelopers.add(developerQueryService.findById(developerId));
        }
        article.setDevelopers(updatedDevelopers);
    }

}
