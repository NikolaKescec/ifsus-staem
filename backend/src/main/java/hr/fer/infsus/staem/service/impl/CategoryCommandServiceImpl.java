package hr.fer.infsus.staem.service.impl;

import hr.fer.infsus.staem.entity.Article;
import hr.fer.infsus.staem.entity.Category;
import hr.fer.infsus.staem.repository.CategoryRepository;
import hr.fer.infsus.staem.service.CategoryCommandService;
import hr.fer.infsus.staem.service.CategoryQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryCommandServiceImpl implements CategoryCommandService {

    private final CategoryRepository categoryRepository;

    private final CategoryQueryService categoryQueryService;

    @Override
    public Category create(String name) {
        final Category category = new Category();
        category.setName(name);

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, String name) {
        final Category category = categoryQueryService.findById(id);
        category.setName(name);

        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        final Category category = categoryQueryService.findById(id);
//        category.getArticles().forEach(article -> article.removeCategory(category));

        for(Article article : category.getArticles()) {
            category.removeArticle(article);
        }


        categoryRepository.delete(category);
    }

}
