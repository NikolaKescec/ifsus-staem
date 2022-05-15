package hr.fer.infsus.staem.service


import hr.fer.infsus.staem.entity.Genre_
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.GenreRepository
import hr.fer.infsus.staem.service.impl.GenreQueryServiceImpl
import hr.fer.infsus.staem.testBuilders.GenreTestBuilder
import org.springframework.data.domain.Sort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(GenreQueryService)
@ActiveProfiles("test")
class GenreQueryServiceTest extends Specification {

  GenreRepository genreRepository = Mock()

  def genreQueryService = new GenreQueryServiceImpl(genreRepository)

  def "should return two sorted genres by name for find all"() {
    given:
    def givenCategories =
        [GenreTestBuilder.builder().def().withName("A").build(), GenreTestBuilder.builder().def().withId(2L).withName("B").build()]

    when: "query all genres"
    def result = genreQueryService.findAll()

    then:
    1 * genreRepository.findAll(Sort.by(Genre_.NAME)) >> givenCategories
    result.size() == 2
    result == givenCategories
  }

  def "should return queried category by id"() {
    given:
    def givenGenre = GenreTestBuilder.builder().def().withName("A").build()

    when: "query genre"
    def result = genreQueryService.findById(1L)

    then:
    1 * genreRepository.findById(1L) >> Optional.of(givenGenre)
    result == givenGenre
  }

  def "should throw EntityNotFound for nonexistent entity"() {
    when: "query genre"
    genreQueryService.findById(1L)

    then:
    1 * genreRepository.findById(1L) >> Optional.empty()
    thrown(EntityNotFoundException)
  }


}
