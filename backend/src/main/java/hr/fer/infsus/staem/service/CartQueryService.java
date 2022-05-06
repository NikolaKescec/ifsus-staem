package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Cart;

public interface CartQueryService {

    Cart findByUserId(Long userId);

    boolean existsById(Long id);

}
