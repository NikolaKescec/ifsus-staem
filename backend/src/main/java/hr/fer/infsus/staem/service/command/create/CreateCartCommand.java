package hr.fer.infsus.staem.service.command.create;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCartCommand {

    private List<Long> articles;

}
