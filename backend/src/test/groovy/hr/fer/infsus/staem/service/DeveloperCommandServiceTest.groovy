package hr.fer.infsus.staem.service

import hr.fer.infsus.staem.entity.Developer
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.DeveloperRepository
import hr.fer.infsus.staem.service.impl.DeveloperCommandServiceImpl
import hr.fer.infsus.staem.testBuilders.DeveloperTestBuilder
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(DeveloperCommandService)
@ActiveProfiles("test")
class DeveloperCommandServiceTest extends Specification {

  DeveloperRepository developerRepository = Mock()

  DeveloperQueryService developerQueryService = Mock()

  def developerCommandService = new DeveloperCommandServiceImpl(developerRepository, developerQueryService)

  def "should create new developer"() {
    given:
    def developerName = "developerName"

    when: "create developer"
    def result = developerCommandService.create(developerName)

    then:
    1 * developerRepository.save(_ as Developer) >> DeveloperTestBuilder.builder().withId(1L).withName(developerName).build()
    result.name == developerName
  }

  def "should update developer"() {
    given:
    def developerId = 1L
    def developerName = "hr.fer.infsus.staem.entity.DeveloperName"

    when: "update developer"
    def result = developerCommandService.update(developerId, developerName)

    then:
    1 * developerQueryService.findById(1L) >> DeveloperTestBuilder.builder().withId(1L).withName("oldName").build()
    1 * developerRepository.save(_ as Developer) >> DeveloperTestBuilder.builder().withId(1L).withName(developerName).build()
    result.id == 1
    result.name == developerName
  }

  def "should delete developer"() {
    given:
    def developerId = 1L

    when: "query for entity"
    developerCommandService.delete(developerId)
    developerQueryService.findById(developerId)

    then:
    2 * developerQueryService.findById(developerId) >>>
        DeveloperTestBuilder.builder().withId(1L).withName("oldName").build() >> { throw new EntityNotFoundException(Developer.class, new String[]{1L}) }
    1 * developerRepository.delete(_ as Developer)
    thrown(EntityNotFoundException)
  }


}
