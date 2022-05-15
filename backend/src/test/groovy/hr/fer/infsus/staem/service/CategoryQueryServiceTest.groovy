package hr.fer.infsus.staem.service

import hr.fer.infsus.staem.entity.Category_
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.CategoryRepository
import hr.fer.infsus.staem.service.impl.CategoryQueryServiceImpl
import hr.fer.infsus.staem.testBuilders.CategoryTestBuilder
import org.springframework.data.domain.Sort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(CategoryQueryService)
@ActiveProfiles("test")
class CategoryQueryServiceTest extends Specification {

  CategoryRepository categoryRepository = Mock()

  def categoryQueryService = new CategoryQueryServiceImpl(categoryRepository)

  def "should return two sorted category by name for find all"() {
    given:
    def givenCategories =
        [CategoryTestBuilder.builder().def().withName("A").build(), CategoryTestBuilder.builder().def().withId(2L).withName("B").build()]

    when: "query all entities"
    def result = categoryQueryService.findAll()

    then:
    1 * categoryRepository.findAll(Sort.by(Category_.NAME)) >> givenCategories
    result.size() == 2
    result == givenCategories
  }

  def "should return queried category by id"() {
    given:
    def givenCategory = CategoryTestBuilder.builder().def().withName("A").build()

    when: "query for category"
    def result = categoryQueryService.findById(1L)

    then:
    1 * categoryRepository.findById(1L) >> Optional.of(givenCategory)
    result == givenCategory
  }

  def "should throw EntityNotFound for nonexistent entity"() {
    when: "query for entity"
    categoryQueryService.findById(1L)

    then:
    1 * categoryRepository.findById(1L) >> Optional.empty()
    thrown(EntityNotFoundException)
  }


}
