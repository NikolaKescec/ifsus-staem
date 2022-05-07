package hr.fer.infsus.staem.validator.validdlc;

import hr.fer.infsus.staem.controller.request.create.CreateArticleRequest;
import hr.fer.infsus.staem.entity.ArticleType;

import java.util.List;

public interface ValidDlcFields {

    ArticleType getArticleType();

    List<CreateArticleRequest> getDlcs();

}

