package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Genre;

public interface GenreCommandService {

    Genre create(String name);

    Genre update(Long id, String name);

    void delete(Long id);

}
