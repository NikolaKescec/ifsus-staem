package hr.fer.infsus.staem.service

import hr.fer.infsus.staem.entity.Publisher_
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.PublisherRepository
import hr.fer.infsus.staem.service.impl.PublisherQueryServiceImpl
import hr.fer.infsus.staem.testBuilders.PublisherTestBuilder
import org.springframework.data.domain.Sort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(PublisherQueryService)
@ActiveProfiles("h2")
class PublisherQueryServiceTest extends Specification {

  PublisherRepository publisherRepository = Mock()

  def developerQueryService = new PublisherQueryServiceImpl(publisherRepository)

  def "should return two sorted publishers by name for find all"() {
    given:
    def givenPublishers =
        [PublisherTestBuilder.builder().def().withName("A").build(), PublisherTestBuilder.builder().def().withId(2L).withName("B").build()]

    when: "query all entities"
    def result = developerQueryService.findAll()

    then:
    1 * publisherRepository.findAll(Sort.by(Publisher_.NAME)) >> givenPublishers
    result.size() == 2
    result == givenPublishers
  }

  def "should return queried publisher by id"() {
    given:
    def givenDeveloper = PublisherTestBuilder.builder().def().withName("A").build()

    when: "query for publisher"
    def result = developerQueryService.findById(1L)

    then:
    1 * publisherRepository.findById(1L) >> Optional.of(givenDeveloper)
    result == givenDeveloper
  }

  def "should throw EntityNotFound for nonexistent entity"() {
    when: "query for entity"
    developerQueryService.findById(1L)

    then:
    1 * publisherRepository.findById(1L) >> Optional.empty()
    thrown(EntityNotFoundException)
  }


}
