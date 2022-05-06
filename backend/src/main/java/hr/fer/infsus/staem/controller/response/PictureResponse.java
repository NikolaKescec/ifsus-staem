package hr.fer.infsus.staem.controller.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PictureResponse implements Serializable {

    private String urlFull;

    private String urlThumbnail;

}
