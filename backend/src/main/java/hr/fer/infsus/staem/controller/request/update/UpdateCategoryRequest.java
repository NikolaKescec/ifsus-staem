package hr.fer.infsus.staem.controller.request.update;

import hr.fer.infsus.staem.repository.CategoryRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryRequest {

    @NotNull(message = "Category id is required")
    @EntityExists(repository = CategoryRepository.class, message = "Category with given id does not exist")
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

}
