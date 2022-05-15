package hr.fer.infsus.staem.controller

import hr.fer.infsus.staem.controller.request.create.CreateCategoryRequest
import hr.fer.infsus.staem.controller.request.update.UpdateCategoryRequest
import hr.fer.infsus.staem.controller.response.CategoryResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Subject

@Subject(ArticleController.class)
@Transactional
@SpringBootTest
@Sql(["/db/test-schema.sql", "/db/test-category.sql"])
@ActiveProfiles("test")
class CategoryControllerIntegrationTest extends Specification {

  @Autowired
  CategoryController categoryController


  def "get all categories"() {
    when:
    List<CategoryResponse> result = categoryController.findAll()

    then:
    result.size() == 2
    verifyAll(result.get(0)) {
      it.id == 1
      it.name == "name 1"
    }
    verifyAll(result.get(1)) {
      it.id == 2
      it.name == "name 2"
    }
  }

  def "create category"() {
    given:
    def categoryRequest = new CreateCategoryRequest()
    categoryRequest.setName("name")

    when:
    CategoryResponse result = categoryController.create(categoryRequest)

    then:
    verifyAll(result) {
      it.id == 3
      it.name == categoryRequest.name
    }
  }

  def "update category"() {
    given:
    def categoryRequest = new UpdateCategoryRequest();
    categoryRequest.setId(1L)
    categoryRequest.setName("new name")

    when:
    CategoryResponse result = categoryController.update(1L, categoryRequest)

    then:
    verifyAll(result) {
      it.id == categoryRequest.id
      it.name == categoryRequest.name
    }
  }

  def "delete category"() {
    when:
    categoryController.delete(1L)
    List<CategoryResponse> result = categoryController.findAll()

    then:
    result.size() == 1
    verifyAll(result.get(0)) {
      it.id == 2
    }
  }

}
