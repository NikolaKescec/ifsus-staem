package hr.fer.infsus.staem.controller

import hr.fer.infsus.staem.controller.request.create.CreateCartRequest
import hr.fer.infsus.staem.controller.response.ArticleResponse
import hr.fer.infsus.staem.exception.ArticleAlreadyBought
import hr.fer.infsus.staem.testBuilders.UserInfoTestBuilder
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
@Sql(["/db/test-schema.sql", "/db/test-data.sql"])
@ActiveProfiles("test")
class CartControllerIntegrationTest extends Specification {

  @Autowired
  CartController cartController

  @Autowired
  ArticleController articleController

  def "buy first time"() {
    given:
    def userInfo = UserInfoTestBuilder.builder().def().withSubject('NEW_USER').withEmail("pomozimiemail@gmail.com").build();
    def createCartRequest = new CreateCartRequest()
    createCartRequest.setArticles(List.of(1L, 2L, 3L))

    when:
    List<ArticleResponse> old_result = articleController.findBought(userInfo)
    cartController.create(userInfo, createCartRequest)
    List<ArticleResponse> result = articleController.findBought(userInfo).sort((a1, a2) -> a1.getId() <=> a2.getId())

    then:
    old_result.size() == 0
    result.size() == 3
    verifyAll(result.get(0)) {
      it.id == 1
      it.title == "Article 1"
      it.price == 100
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
    }
    verifyAll(result.get(1)) {
      it.id == 2
      it.title == "Article 2"
      it.price == 200
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
    }
    verifyAll(result.get(2)) {
      it.id == 3
      it.title == "Article 3"
      it.price == 300
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
    }
  }

  def "buy second time"() {
    given:
    def userInfo = UserInfoTestBuilder.builder().def().withSubject('USER_2').withEmail("pomozimiemail@gmail.com").build();
    def createCartRequest = new CreateCartRequest()
    createCartRequest.setArticles(List.of(1L))

    when:
    List<ArticleResponse> old_result = articleController.findBought(userInfo)
    cartController.create(userInfo, createCartRequest)
    List<ArticleResponse> result = articleController.findBought(userInfo).sort((a1, a2) -> a1.getId() <=> a2.getId())

    then:
    old_result.size() == 2
    result.size() == 3
    verifyAll(result.get(0)) {
      it.id == 1
      it.title == "Article 1"
      it.price == 100
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
    }
    verifyAll(result.get(1)) {
      it.id == 2
      it.title == "Article 2"
      it.price == 200
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
    }
    verifyAll(result.get(2)) {
      it.id == 3
      it.title == "Article 3"
      it.price == 300
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
    }
  }

  def "buy already bought"() {
    given:
    def userInfo = UserInfoTestBuilder.builder().def().withSubject('USER_1').withEmail("pomozimiemail@gmail.com").build();
    def createCartRequest = new CreateCartRequest()
    createCartRequest.setArticles(List.of(1L))

    when:
    cartController.create(userInfo, createCartRequest)

    then:
    thrown(ArticleAlreadyBought)
  }

}
