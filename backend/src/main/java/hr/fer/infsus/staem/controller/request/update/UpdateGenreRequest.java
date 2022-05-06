package hr.fer.infsus.staem.controller.request.update;

import hr.fer.infsus.staem.repository.GenreRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateGenreRequest {

    @NotNull(message = "Genre id is required")
    @EntityExists(repository = GenreRepository.class, message = "Genre with given id does not exist")
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

}
