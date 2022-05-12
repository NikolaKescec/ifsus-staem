package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Cart;
import hr.fer.infsus.staem.entity.Users;
import hr.fer.infsus.staem.service.command.create.CreateCartCommand;

public interface CartCommandService {

    Cart create(Users user, CreateCartCommand createCartCommand);

}
