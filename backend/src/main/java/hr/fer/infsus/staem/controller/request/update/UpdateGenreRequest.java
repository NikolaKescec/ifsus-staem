package hr.fer.infsus.staem.controller.request.update;

import hr.fer.infsus.staem.repository.GenreRepository;
import hr.fer.infsus.staem.validator.entityexists.EntityExists;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateGenreRequest {

    @NotNull(message = "Genre id is required")
    @EntityExists(repository = GenreRepository.class, message = "Genre with given id does not exist")
    private Long id;

    @NotEmpty(message = "Genre name is required")
    @Size(min = 1, max = 255, message = "Genre name must be between 1 and 255 characters")
    private String name;

}
