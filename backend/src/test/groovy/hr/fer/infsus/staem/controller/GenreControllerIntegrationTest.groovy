package hr.fer.infsus.staem.controller

import hr.fer.infsus.staem.controller.request.create.CreateGenreRequest
import hr.fer.infsus.staem.controller.request.update.UpdateGenreRequest
import hr.fer.infsus.staem.controller.response.GenreResponse
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
@Sql(["/db/test-schema.sql", "/db/test-genre.sql"])
@ActiveProfiles("test")
class GenreControllerIntegrationTest extends Specification {

  @Autowired
  GenreController genreController


  def "get all categories"() {
    when:
    List<GenreResponse> result = genreController.findAll()

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

  def "create genre"() {
    given:
    def genreRequest = new CreateGenreRequest()
    genreRequest.setName("name")

    when:
    GenreResponse result = genreController.create(genreRequest)

    then:
    verifyAll(result) {
      it.id == 3
      it.name == genreRequest.name
    }
  }

  def "update genre"() {
    given:
    def genreRequest = new UpdateGenreRequest();
    genreRequest.setId(1L)
    genreRequest.setName("new name")

    when:
    GenreResponse result = genreController.update(1L, genreRequest)

    then:
    verifyAll(result) {
      it.id == genreRequest.id
      it.name == genreRequest.name
    }
  }

  def "delete genre"() {
    when:
    genreController.delete(1L)
    List<GenreResponse> result = genreController.findAll()

    then:
    result.size() == 1
    verifyAll(result.get(0)) {
      it.id == 2
    }
  }

}
