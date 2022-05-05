package hr.fer.infsus.staem.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PublisherResponse implements Serializable {

    private Long id;
    private String name;

}
