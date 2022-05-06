package hr.fer.infsus.staem.controller.request.update;

import hr.fer.infsus.staem.repository.DeveloperRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateDeveloperRequest {

    @NotNull(message = "Developer id is required")
    @EntityExists(repository = DeveloperRepository.class, message = "Developer with given id does not exist")
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

}
