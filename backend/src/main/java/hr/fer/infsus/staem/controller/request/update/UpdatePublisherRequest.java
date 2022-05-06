package hr.fer.infsus.staem.controller.request.update;

import hr.fer.infsus.staem.repository.PublisherRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePublisherRequest {

    @NotNull(message = "Publisher id is required")
    @EntityExists(repository = PublisherRepository.class, message = "Publisher with given id does not exist")
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

}
