package hr.fer.infsus.staem.controller.request.shared;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PictureRequest {

    @NotEmpty(message = "Picture url is required")
    private String urlFull;

    @NotEmpty(message = "Picture url thumbnail is required")
    private String urlThumbnail;

}
