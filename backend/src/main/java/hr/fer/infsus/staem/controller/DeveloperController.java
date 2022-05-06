package hr.fer.infsus.staem.controller;

import hr.fer.infsus.staem.controller.request.create.CreateDeveloperRequest;
import hr.fer.infsus.staem.controller.request.update.UpdateDeveloperRequest;
import hr.fer.infsus.staem.controller.response.DeveloperResponse;
import hr.fer.infsus.staem.controller.response.GenreResponse;
import hr.fer.infsus.staem.exception.IdMismatchException;
import hr.fer.infsus.staem.mapper.GenericCreateMapper;
import hr.fer.infsus.staem.service.DeveloperCommandService;
import hr.fer.infsus.staem.service.DeveloperQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/developer")
@AllArgsConstructor
public class DeveloperController {

    private DeveloperCommandService developerCommandService;

    private DeveloperQueryService developerQueryService;

    private GenericCreateMapper genericCreateMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeveloperResponse> findAll() {
        return genericCreateMapper.mapToList(developerQueryService.findAll(), DeveloperResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse create(@RequestBody @Valid CreateDeveloperRequest createDeveloperRequest) {
        return genericCreateMapper.map(createDeveloperRequest.getName(), DeveloperResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public GenreResponse update(@PathVariable("id") Long id,
        @RequestBody @Valid UpdateDeveloperRequest updateDeveloperRequest) {
        if (!id.equals(updateDeveloperRequest.getId())) {
            throw new IdMismatchException(id, updateDeveloperRequest.getId());
        }

        return genericCreateMapper.map(
            developerCommandService.update(updateDeveloperRequest.getId(), updateDeveloperRequest.getName()),
            GenreResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id) {
        developerCommandService.delete(id);
    }

}
