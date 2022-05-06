package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Category;
import hr.fer.infsus.staem.exception.EntityNotFoundException;
import hr.fer.infsus.staem.repository.CategoryRepository;
import hr.fer.infsus.staem.service.CategoryQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(Category.class, new String[] { "id" }));
    }

}
