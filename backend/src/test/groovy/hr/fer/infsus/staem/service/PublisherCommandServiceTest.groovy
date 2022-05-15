package hr.fer.infsus.staem.service

import hr.fer.infsus.staem.entity.Publisher
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.PublisherRepository
import hr.fer.infsus.staem.service.impl.PublisherCommandServiceImpl
import hr.fer.infsus.staem.testBuilders.PublisherTestBuilder
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(PublisherCommandService)
@ActiveProfiles("test")
class PublisherCommandServiceTest extends Specification {

  PublisherRepository developerRepository = Mock()

  PublisherQueryService developerQueryService = Mock()

  def developerCommandService = new PublisherCommandServiceImpl(developerRepository, developerQueryService)

  def "should create new publisher"() {
    given:
    def developerName = "developerName"

    when: "create publisher"
    def result = developerCommandService.create(developerName)

    then:
    1 * developerRepository.save(_ as Publisher) >> PublisherTestBuilder.builder().withId(1L).withName(developerName).build()
    result.name == developerName
  }

  def "should update publisher"() {
    given:
    def developerId = 1L
    def developerName = "hr.fer.infsus.staem.entity.PublisherName"

    when: "update publisher"
    def result = developerCommandService.update(developerId, developerName)

    then:
    1 * developerQueryService.findById(1L) >> PublisherTestBuilder.builder().withId(1L).withName("oldName").build()
    1 * developerRepository.save(_ as Publisher) >> PublisherTestBuilder.builder().withId(1L).withName(developerName).build()
    result.id == 1
    result.name == developerName
  }

  def "should delete publisher"() {
    given:
    def developerId = 1L

    when: "query for entity"
    developerCommandService.delete(developerId)
    developerQueryService.findById(developerId)

    then:
    1 * developerQueryService.findById(developerId) >> { throw new EntityNotFoundException(Publisher.class, new String[]{1L}) }
    1 * developerRepository.deleteById(developerId)
    thrown(EntityNotFoundException)
  }


}
