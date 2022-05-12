package hr.fer.infsus.staem.controller;

import hr.fer.infsus.staem.controller.request.create.CreateArticleRequest;
import hr.fer.infsus.staem.controller.request.update.UpdateArticleRequest;
import hr.fer.infsus.staem.controller.response.ArticleDetailsResponse;
import hr.fer.infsus.staem.controller.response.ArticleResponse;
import hr.fer.infsus.staem.mapper.GenericCreateMapper;
import hr.fer.infsus.staem.repository.query.FindArticleQuery;
import hr.fer.infsus.staem.service.ArticleCommandService;
import hr.fer.infsus.staem.service.ArticleQueryService;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;
import hr.fer.infsus.staem.service.command.update.UpdateArticleCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;

    private final ArticleCommandService articleCommandService;

    private final GenericCreateMapper genericCreateMapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Page<ArticleResponse> findByQuery(@Valid FindArticleQuery findArticleQuery) {
        return genericCreateMapper.mapToPage(articleQueryService.findByQuery(findArticleQuery), ArticleResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ArticleDetailsResponse findById(@PathVariable("id") Long id) {
        return genericCreateMapper.map(articleQueryService.findById(id), ArticleDetailsResponse.class);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ArticleDetailsResponse create(@RequestBody @Valid CreateArticleRequest createArticleRequest) {
        return genericCreateMapper.map(
            articleCommandService.create(genericCreateMapper.map(createArticleRequest, CreateArticleCommand.class)),
            ArticleDetailsResponse.class);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ArticleDetailsResponse create(@RequestBody @Valid UpdateArticleRequest updateArticleRequest) {
        return genericCreateMapper.map(
            articleCommandService.update(genericCreateMapper.map(updateArticleRequest, UpdateArticleCommand.class)),
            ArticleDetailsResponse.class);
    }

}
