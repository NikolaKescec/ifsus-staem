package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Developer;

public interface DeveloperCommandService {

    Developer create(String name);

    Developer update(Long id, String name);

    void delete(Long id);

}
