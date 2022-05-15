package hr.fer.infsus.staem.service

import hr.fer.infsus.staem.entity.Developer_
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.DeveloperRepository
import hr.fer.infsus.staem.service.impl.DeveloperQueryServiceImpl
import hr.fer.infsus.staem.testBuilders.DeveloperTestBuilder
import org.springframework.data.domain.Sort
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(DeveloperQueryService)
@ActiveProfiles("test")
class DeveloperQueryServiceTest extends Specification {

  DeveloperRepository developerRepository = Mock()

  def developerQueryService = new DeveloperQueryServiceImpl(developerRepository)

  def "should return two sorted developers by name for find all"() {
    given:
    def givenDevelopers =
        [DeveloperTestBuilder.builder().def().withName("A").build(), DeveloperTestBuilder.builder().def().withId(2L).withName("B").build()]

    when: "query all entities"
    def result = developerQueryService.findAll()

    then:
    1 * developerRepository.findAll(Sort.by(Developer_.NAME)) >> givenDevelopers
    result.size() == 2
    result == givenDevelopers
  }

  def "should return developer entity by id"() {
    given:
    def givenDeveloper = DeveloperTestBuilder.builder().def().withName("A").build()

    when: "query for entity"
    def result = developerQueryService.findById(1L)

    then:
    1 * developerRepository.findById(1L) >> Optional.of(givenDeveloper)
    result == givenDeveloper
  }

  def "should throw EntityNotFound for nonexistent entity"() {
    when: "query for entity"
    developerQueryService.findById(1L)

    then:
    1 * developerRepository.findById(1L) >> Optional.empty()
    thrown(EntityNotFoundException)
  }


}
