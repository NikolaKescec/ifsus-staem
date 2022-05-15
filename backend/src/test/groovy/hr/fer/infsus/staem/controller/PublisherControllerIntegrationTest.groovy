package hr.fer.infsus.staem.controller

import hr.fer.infsus.staem.controller.request.create.CreatePublisherRequest
import hr.fer.infsus.staem.controller.request.update.UpdatePublisherRequest
import hr.fer.infsus.staem.controller.response.PublisherResponse
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
@Sql(["/db/test-schema.sql", "/db/test-publisher.sql"])
@ActiveProfiles("test")
class PublisherControllerIntegrationTest extends Specification {

  @Autowired
  PublisherController publisherController


  def "get all categories"() {
    when:
    List<PublisherResponse> result = publisherController.findAll()

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

  def "create publisher"() {
    given:
    def publisherRequest = new CreatePublisherRequest()
    publisherRequest.setName("name")

    when:
    PublisherResponse result = publisherController.create(publisherRequest)

    then:
    verifyAll(result) {
      it.id == 3
      it.name == publisherRequest.name
    }
  }

  def "update publisher"() {
    given:
    def publisherRequest = new UpdatePublisherRequest();
    publisherRequest.setId(1L)
    publisherRequest.setName("new name")

    when:
    PublisherResponse result = publisherController.update(1L, publisherRequest)

    then:
    verifyAll(result) {
      it.id == publisherRequest.id
      it.name == publisherRequest.name
    }
  }

  def "delete publisher"() {
    when:
    publisherController.delete(1L)
    List<PublisherResponse> result = publisherController.findAll()

    then:
    result.size() == 1
    verifyAll(result.get(0)) {
      it.id == 2
    }
  }

}
