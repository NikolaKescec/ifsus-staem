package hr.fer.infsus.staem.service;

import hr.fer.infsus.staem.entity.Category;

import java.util.List;

public interface CategoryQueryService {

    List<Category> findAll();

    Category findById(Long id);

}
