package hr.fer.infsus.staem.service

import hr.fer.infsus.staem.entity.Genre
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.GenreRepository
import hr.fer.infsus.staem.service.impl.GenreCommandServiceImpl
import hr.fer.infsus.staem.testBuilders.GenreTestBuilder
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(GenreCommandService)
@ActiveProfiles("test")
class GenreCommandServiceTest extends Specification {

  GenreRepository genreRepository = Mock()

  GenreQueryService genreQueryService = Mock()

  def genreCommandService = new GenreCommandServiceImpl(genreRepository, genreQueryService)

  def "should create new genre"() {
    given:
    def genreName = "genreName"

    when: "create genre"
    def result = genreCommandService.create(genreName)

    then:
    1 * genreRepository.save(_ as Genre) >> GenreTestBuilder.builder().withId(1L).withName(genreName).build()
    result.name == genreName
  }

  def "should update genre"() {
    given:
    def genreId = 1L
    def genreName = "hr.fer.infsus.staem.entity.GenreName"

    when: "update genre"
    def result = genreCommandService.update(genreId, genreName)

    then:
    1 * genreQueryService.findById(1L) >> GenreTestBuilder.builder().withId(1L).withName("oldName").build()
    1 * genreRepository.save(_ as Genre) >> GenreTestBuilder.builder().withId(1L).withName(genreName).build()
    result.id == 1
    result.name == genreName
  }

  def "should delete genre"() {
    given:
    def genreId = 1L

    when: "query for entity"
    genreCommandService.delete(genreId)
    genreQueryService.findById(genreId)

    then:
    1 * genreQueryService.findById(genreId) >> { throw new EntityNotFoundException(Genre.class, new String[]{1L}) }
    1 * genreRepository.deleteById(genreId)
    thrown(EntityNotFoundException)
  }


}
