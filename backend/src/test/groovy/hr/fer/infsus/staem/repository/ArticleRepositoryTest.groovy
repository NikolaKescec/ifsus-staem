package hr.fer.infsus.staem.repository

import hr.fer.infsus.staem.entity.Article
import hr.fer.infsus.staem.repository.query.PriceRange
import hr.fer.infsus.staem.testBuilders.FindArticleQueryTestBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Page
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Subject

@Subject(ArticleRepositoryTest)
@Transactional
@DataJpaTest
@Sql(["/db/test-schema.sql", "/db/test-data.sql"])
@ActiveProfiles("test")
class ArticleRepositoryTest extends Specification {

  @Autowired
  ArticleRepository articleRepository

  def "should return empty list"() {
    given:
    def query = FindArticleQueryTestBuilder.builder().def().build();

    when:
    Page<Article> result = articleRepository.findByQuery(query)

    then:
    result.totalElements == 0
    result.totalPages == 0
  }

  def "should return all articles with specified title, developer, category, publisher and price list"() {
    given:
    def priceRange = new PriceRange()
    priceRange.setMinPrice(50)
    priceRange.setMaxPrice(200)
    def query = FindArticleQueryTestBuilder.builder().def()
        .withCategoryId(1)
        .withDeveloperId(1)
        .withPublisherId(1)
        .withTitle("Article")
        .withPriceRange(priceRange)
        .build();

    when:
    Page<Article> result = articleRepository.findByQuery(query)

    then:
    result.totalElements == 2
    result.totalPages == 1
    result.content == [articleRepository.findById(1L).get(), articleRepository.findById(2L).get()]
  }


}
