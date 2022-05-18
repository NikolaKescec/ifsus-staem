package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.security.UserInfo;
import hr.fer.infsus.staem.service.command.create.CreateCartCommand;

public interface CartSagaService {

    void create(UserInfo currentUserInfo, CreateCartCommand createCartCommand);

}
