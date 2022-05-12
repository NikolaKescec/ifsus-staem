package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;
import hr.fer.infsus.staem.service.command.update.UpdateArticleCommand;

public interface ArticleCommandService {

    Article create(CreateArticleCommand createArticleCommand);

    Article update(UpdateArticleCommand createArticleCommand);

    void delete(Long id);

}
