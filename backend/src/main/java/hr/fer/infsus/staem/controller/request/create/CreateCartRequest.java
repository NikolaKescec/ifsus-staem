package hr.fer.infsus.staem.controller.request.create;

import hr.fer.infsus.staem.repository.ArticleRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCartRequest {

    private List<@EntityExists(repository = ArticleRepository.class) Long> articles;

}
