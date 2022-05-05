package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Category;
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

}
