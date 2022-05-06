package hr.fer.infsus.staem.controller.request.create;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateGenreRequest {

    @NotEmpty(message = "Genre name is required")
    @Size(min = 1, max = 255, message = "Genre name must be between 1 and 255 characters")
    private String name;

}
