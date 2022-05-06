package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Genre;
import hr.fer.infsus.staem.repository.GenreRepository;
import hr.fer.infsus.staem.service.GenreCommandService;
import hr.fer.infsus.staem.service.GenreQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreCommandServiceImpl implements GenreCommandService {

    private final GenreRepository genreRepository;

    private final GenreQueryService genreQueryService;

    @Override
    public Genre create(String name) {
        final Genre genre = new Genre();
        genre.setName(name);

        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Long id, String name) {
        final Genre genre = genreQueryService.findById(id);
        genre.setName(name);

        return genreRepository.save(genre);
    }

    @Override
    public void delete(Long id) {
        final Genre genre = genreQueryService.findById(id);
        genre.getArticles().forEach(article -> article.removeGenre(genre));

        genreRepository.deleteById(id);
    }

}
