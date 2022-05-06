package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Genre;

import java.util.List;

public interface GenreQueryService {

    List<Genre> findAll();

    Genre findById(Long id);

}
