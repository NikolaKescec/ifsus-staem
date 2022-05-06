package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Category;

public interface CategoryCommandService {

    Category create(String name);

    Category update(Long id, String name);

    void delete(Long id);

}
