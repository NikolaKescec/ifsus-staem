package hr.fer.infsus.staem.controller

import hr.fer.infsus.staem.controller.request.create.CreateDeveloperRequest
import hr.fer.infsus.staem.controller.request.update.UpdateDeveloperRequest
import hr.fer.infsus.staem.controller.response.DeveloperResponse
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
@Sql(["/db/test-schema.sql", "/db/test-developer.sql"])
@ActiveProfiles("test")
class DeveloperControllerIntegrationTest extends Specification {

  @Autowired
  DeveloperController developerController


  def "get all categories"() {
    when:
    List<DeveloperResponse> result = developerController.findAll()

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

  def "create developer"() {
    given:
    def developerRequest = new CreateDeveloperRequest()
    developerRequest.setName("name")

    when:
    DeveloperResponse result = developerController.create(developerRequest)

    then:
    verifyAll(result) {
      it.id == 3
      it.name == developerRequest.name
    }
  }

  def "update developer"() {
    given:
    def developerRequest = new UpdateDeveloperRequest();
    developerRequest.setId(1L)
    developerRequest.setName("new name")

    when:
    DeveloperResponse result = developerController.update(1L, developerRequest)

    then:
    verifyAll(result) {
      it.id == developerRequest.id
      it.name == developerRequest.name
    }
  }

  def "delete developer"() {
    when:
    developerController.delete(1L)
    List<DeveloperResponse> result = developerController.findAll()

    then:
    result.size() == 1
    verifyAll(result.get(0)) {
      it.id == 2
    }
  }

}
