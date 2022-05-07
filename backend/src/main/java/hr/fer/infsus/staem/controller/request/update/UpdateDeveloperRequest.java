package hr.fer.infsus.staem.controller.request.update;

import hr.fer.infsus.staem.repository.DeveloperRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateDeveloperRequest {

    @NotNull(message = "Developer id is required")
    @EntityExists(repository = DeveloperRepository.class, message = "Developer with given id does not exist")
    private Long id;

    @NotEmpty(message = "Developer name is required")
    @Size(min = 1, max = 255, message = "Developer name must be between 1 and 255 characters")
    private String name;

}
