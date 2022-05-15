package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Users;

public interface UsersQueryService {

    Users findById(String id);

}
