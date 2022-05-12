package hr.fer.infsus.staem.controller;

import hr.fer.infsus.staem.controller.request.create.CreateGenreRequest;
import hr.fer.infsus.staem.controller.request.update.UpdateGenreRequest;
import hr.fer.infsus.staem.controller.response.GenreResponse;
import hr.fer.infsus.staem.exception.IdMismatchException;
import hr.fer.infsus.staem.mapper.core.GenericCreateMapper;
import hr.fer.infsus.staem.service.GenreCommandService;
import hr.fer.infsus.staem.service.GenreQueryService;
import lombok.AllArgsConstructor;
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

@RestController
@RequestMapping("/genre")
@AllArgsConstructor
public class GenreController {

    private GenreCommandService genreCommandService;

    private GenreQueryService genreQueryService;

    private GenericCreateMapper genericCreateMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreResponse> findAll() {
        return genericCreateMapper.mapToList(genreQueryService.findAll(), GenreResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('create:genre')")
    public GenreResponse create(@RequestBody @Valid CreateGenreRequest createGenreRequest) {
        return genericCreateMapper.map(genreCommandService.create(createGenreRequest.getName()), GenreResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('update:genre')")
    public GenreResponse update(@PathVariable("id") Long id,
        @RequestBody @Valid UpdateGenreRequest updateGenreRequest) {
        if (!id.equals(updateGenreRequest.getId())) {
            throw new IdMismatchException(id, updateGenreRequest.getId());
        }

        return genericCreateMapper.map(
            genreCommandService.update(updateGenreRequest.getId(), updateGenreRequest.getName()),
            GenreResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('delete:genre')")
    public void delete(@PathVariable("id") Long id) {
        genreCommandService.delete(id);
    }

}
