package hr.fer.infsus.staem.controller;

import hr.fer.infsus.staem.controller.request.create.CreateArticleRequest;
import hr.fer.infsus.staem.controller.request.update.UpdateArticleRequest;
import hr.fer.infsus.staem.controller.response.ArticleDetailsResponse;
import hr.fer.infsus.staem.controller.response.ArticleResponse;
import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.PurchasedArticles;
import hr.fer.infsus.staem.mapper.core.BiCreateMapper;
import hr.fer.infsus.staem.mapper.core.GenericCreateMapper;
import hr.fer.infsus.staem.repository.query.FindArticleQuery;
import hr.fer.infsus.staem.security.CurrentSubject;
import hr.fer.infsus.staem.service.ArticleCommandService;
import hr.fer.infsus.staem.service.ArticleQueryService;
import hr.fer.infsus.staem.service.PurchasedArticleQueryService;
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand;
import hr.fer.infsus.staem.service.command.update.UpdateArticleCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;

    private final ArticleCommandService articleCommandService;

    private final PurchasedArticleQueryService purchasedArticleQueryService;

    private final GenericCreateMapper genericCreateMapper;

    private final BiCreateMapper<Article, String, ArticleDetailsResponse>
        articleSubjectArticleDetailsResponseBiCreateMapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Page<ArticleResponse> findByQuery(@Valid FindArticleQuery findArticleQuery) {
        return genericCreateMapper.mapToPage(articleQueryService.findByQuery(findArticleQuery), ArticleResponse.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ArticleDetailsResponse findById(@CurrentSubject String currentSubject, @PathVariable("id") Long id) {
        return articleSubjectArticleDetailsResponseBiCreateMapper.map(articleQueryService.findById(id), currentSubject);
    }

    @GetMapping("/bought")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('article:read_bought')")
    public List<ArticleResponse> findBought(@CurrentSubject String currentSubject) {
        return genericCreateMapper.mapToList(
            purchasedArticleQueryService.findBought(currentSubject).stream().map(PurchasedArticles::getArticle)
                .collect(Collectors.toList()), ArticleResponse.class);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('article:create')")
    public ArticleDetailsResponse create(@RequestBody @Valid CreateArticleRequest createArticleRequest) {
        return genericCreateMapper.map(
            articleCommandService.create(genericCreateMapper.map(createArticleRequest, CreateArticleCommand.class)),
            ArticleDetailsResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAuthority('article:update')")
    public ArticleDetailsResponse update(@PathVariable("id") Long id,
        @RequestBody @Valid UpdateArticleRequest updateArticleRequest) {
        final UpdateArticleCommand updateArticleCommand =
            genericCreateMapper.map(updateArticleRequest, UpdateArticleCommand.class);
        updateArticleCommand.setId(id);

        return genericCreateMapper.map(articleCommandService.update(updateArticleCommand),
            ArticleDetailsResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('article:delete')")
    public void delete(@PathVariable("id") Long id) {
        articleCommandService.delete(id);
    }

}
