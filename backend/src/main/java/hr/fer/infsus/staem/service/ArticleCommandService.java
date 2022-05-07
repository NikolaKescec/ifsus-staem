package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;

public interface ArticleCommandService {

    Article create(CreateArticleCommand createArticleCommand);

}
