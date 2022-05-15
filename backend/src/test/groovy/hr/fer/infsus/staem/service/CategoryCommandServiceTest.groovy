package hr.fer.infsus.staem.service

import hr.fer.infsus.staem.entity.Category
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.CategoryRepository
import hr.fer.infsus.staem.service.impl.CategoryCommandServiceImpl
import hr.fer.infsus.staem.testBuilders.CategoryTestBuilder
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(CategoryCommandService)
@ActiveProfiles("test")
class CategoryCommandServiceTest extends Specification {

  CategoryRepository categoryRepository = Mock()

  CategoryQueryService categoryQueryService = Mock()

  def categoryCommandService = new CategoryCommandServiceImpl(categoryRepository, categoryQueryService)

  def "should create new category"() {
    given:
    def categoryName = "categoryName"

    when: "create category"
    def result = categoryCommandService.create(categoryName)

    then:
    1 * categoryRepository.save(_ as Category) >> CategoryTestBuilder.builder().withId(1L).withName(categoryName).build()
    result.name == categoryName
  }

  def "should update category"() {
    given:
    def categoryId = 1L
    def categoryName = "newCategoryName"

    when: "update category"
    def result = categoryCommandService.update(categoryId, categoryName)

    then:
    1 * categoryQueryService.findById(1L) >> CategoryTestBuilder.builder().withId(1L).withName("oldName").build()
    1 * categoryRepository.save(_ as Category) >> CategoryTestBuilder.builder().withId(1L).withName(categoryName).build()
    result.id == 1
    result.name == categoryName
  }

  def "should delete category"() {
    given:
    def categoryId = 1L

    when: "query for entity"
    categoryCommandService.delete(categoryId)
    categoryQueryService.findById(categoryId)

    then:
    2 * categoryQueryService.findById(categoryId) >>>
        CategoryTestBuilder.builder().withId(1L).withName("oldName").build() >> { throw new EntityNotFoundException(Category.class, new String[]{1L}) }
    1 * categoryRepository.delete(_ as Category)
    thrown(EntityNotFoundException)
  }


}
