package hr.fer.infsus.staem.controller.request.update;

import hr.fer.infsus.staem.repository.CategoryRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateCategoryRequest {

    @NotNull(message = "Category id is required")
    @EntityExists(repository = CategoryRepository.class, message = "Category with given id does not exist")
    private Long id;

    @NotEmpty(message = "Category name is required")
    @Size(min = 1, max = 255, message = "Category name must be between 1 and 255 characters")
    private String name;

}
