package hr.fer.infsus.staem.service


import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.repository.ArticleRepository
import hr.fer.infsus.staem.service.impl.ArticleQueryServiceImpl
import hr.fer.infsus.staem.testBuilders.ArticleTestBuilder
import hr.fer.infsus.staem.testBuilders.FindArticleQueryTestBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(ArticleQueryService)
@ActiveProfiles("test")
class ArticleQueryServiceTest extends Specification {

  ArticleRepository articleRepository = Mock()

  def articleQueryService = new ArticleQueryServiceImpl(articleRepository)

  def "should return zero paged articles"() {
    given:
    def givenQuery = FindArticleQueryTestBuilder.builder().def().build()

    when: "query all entities"
    def result = articleQueryService.findByQuery(givenQuery)

    then:
    1 * articleRepository.findByQuery(givenQuery) >> Page.empty()
    result.totalElements == 0
    result.totalPages == 1
  }

  def "should return paged articles"() {
    given:
    def givenQuery = FindArticleQueryTestBuilder.builder().def().withTitle("article").build()

    when: "query all entities"
    def result = articleQueryService.findByQuery(givenQuery)

    then:
    1 * articleRepository.findByQuery(givenQuery) >> new PageImpl<>([ArticleTestBuilder.builder().withTitle("article for game").build()])
    result.totalElements == 1
    result.totalPages == 1
  }


  def "should throw EntityNotFound for nonexistent entity"() {
    when: "query for entity"
    articleQueryService.findById(1L)

    then:
    1 * articleRepository.findById(1L) >> Optional.empty()
    thrown(EntityNotFoundException)
  }

  def "should find one article for id"() {
    when: "query for entity"
    def result = articleQueryService.findById(1L)

    then:
    1 * articleRepository.findById(1L) >> Optional.of(ArticleTestBuilder.builder().withTitle("article for game").build())
    result.properties - ArticleTestBuilder.builder().withTitle("article for game").build().properties == Collections.emptyMap()
  }


}
