package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Users;

public interface UserQueryService {

    Users findById(Long id);

}
