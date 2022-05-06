package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Genre;
import hr.fer.infsus.staem.exception.EntityNotFoundException;
import hr.fer.infsus.staem.repository.GenreRepository;
import hr.fer.infsus.staem.service.GenreQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreQueryServiceImpl implements GenreQueryService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(Genre.class, new String[] { "id" }));
    }

}
