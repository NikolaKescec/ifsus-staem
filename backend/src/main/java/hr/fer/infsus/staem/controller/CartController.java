package hr.fer.infsus.staem.controller;

import hr.fer.infsus.staem.controller.request.create.CreateCartRequest;
import hr.fer.infsus.staem.mapper.core.GenericCreateMapper;
import hr.fer.infsus.staem.security.CurrentSubject;
import hr.fer.infsus.staem.service.CartSagaService;
import hr.fer.infsus.staem.service.command.create.CreateCartCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private final CartSagaService cartSagaService;

    private final GenericCreateMapper genericCreateMapper;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('cart:create')")
    public void create(@CurrentSubject String subject, @RequestBody @Valid CreateCartRequest createCartRequest) {
        cartSagaService.create(subject, genericCreateMapper.map(createCartRequest, CreateCartCommand.class));
    }

}
