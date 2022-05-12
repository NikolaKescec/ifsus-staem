package hr.fer.infsus.staem.controller;

import hr.fer.infsus.staem.controller.request.create.CreateCategoryRequest;
import hr.fer.infsus.staem.controller.request.update.UpdateCategoryRequest;
import hr.fer.infsus.staem.controller.response.CategoryResponse;
import hr.fer.infsus.staem.exception.IdMismatchException;
import hr.fer.infsus.staem.mapper.core.GenericCreateMapper;
import hr.fer.infsus.staem.service.CategoryCommandService;
import hr.fer.infsus.staem.service.CategoryQueryService;
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
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryCommandService categoryCommandService;

    private CategoryQueryService categoryQueryService;

    private GenericCreateMapper genericCreateMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> findAll() {
        return genericCreateMapper.mapToList(categoryQueryService.findAll(), CategoryResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('create:category')")
    public CategoryResponse create(@RequestBody @Valid CreateCategoryRequest createCategoryRequest) {
        return genericCreateMapper.map(
            categoryCommandService.create(createCategoryRequest.getName()), CategoryResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('update:category')")
    public CategoryResponse update(@PathVariable("id") Long id,
        @RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
        if (!id.equals(updateCategoryRequest.getId())) {
            throw new IdMismatchException(id, updateCategoryRequest.getId());
        }

        return genericCreateMapper.map(
            categoryCommandService.update(updateCategoryRequest.getId(), updateCategoryRequest.getName()),
            CategoryResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('delete:category')")
    public void delete(@PathVariable("id") Long id) {
        categoryCommandService.delete(id);
    }

}
