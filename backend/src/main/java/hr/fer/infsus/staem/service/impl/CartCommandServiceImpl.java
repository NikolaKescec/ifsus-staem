package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Cart;
import hr.fer.infsus.staem.entity.Users;
import hr.fer.infsus.staem.mapper.core.BiCreateMapper;
import hr.fer.infsus.staem.repository.CartRepository;
import hr.fer.infsus.staem.service.CartCommandService;
import hr.fer.infsus.staem.service.command.create.CreateCartCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartCommandServiceImpl implements CartCommandService {

    private CartRepository cartRepository;

    private BiCreateMapper<Users, CreateCartCommand, Cart> usersCreateCartCommandCartBiCreateMapper;

    @Override
    public Cart create(Users user, CreateCartCommand createCartCommand) {
        final Cart cart = usersCreateCartCommandCartBiCreateMapper.map(user, createCartCommand);

        return cartRepository.save(cart);
    }

}
