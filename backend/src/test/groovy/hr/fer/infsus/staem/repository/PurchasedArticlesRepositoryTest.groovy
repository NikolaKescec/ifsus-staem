package hr.fer.infsus.staem.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.Collectors

@Subject(PurchasedArticlesRepository)
@Transactional
@DataJpaTest
@Sql(["/db/test-schema.sql", "/db/test-data.sql"])
@ActiveProfiles("test")
class PurchasedArticlesRepositoryTest extends Specification {

  @Autowired
  PurchasedArticlesRepository purchasedArticlesRepository

  def "should return true that user has purchased article"() {
    given:
    def userId = "USER_1"
    def articleId = [1L]

    expect:
    purchasedArticlesRepository.existsByUser_IdAndArticle_IdIn(userId, articleId)
  }

  def "should return false that user has purchased article"() {
    given:
    def userId = "USER_2"
    def articleId = [1L]

    expect:
    !purchasedArticlesRepository.existsByUser_IdAndArticle_IdIn(userId, articleId)
  }

  def "should return no articles for user"() {
    given:
    def userId = "USER_3"

    expect:
    purchasedArticlesRepository.findAllByUser_IdOrderByDateOfPurchaseAsc(userId).isEmpty()
  }

  def "should return bought articles for user"() {
    given:
    def userId = "USER_1"

    when:
    def result = purchasedArticlesRepository.findAllByUser_IdOrderByDateOfPurchaseAsc(userId)

    then:
    result.size() == 3
    result.stream().map(purchasedArticle -> purchasedArticle.getArticle().getId()).collect(Collectors.toList()) == [1L, 2L, 3L]
  }

}
