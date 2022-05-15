package hr.fer.infsus.staem.controller

import hr.fer.infsus.staem.controller.request.create.CreateCartRequest
import hr.fer.infsus.staem.controller.response.ArticleResponse
import hr.fer.infsus.staem.exception.ArticleAlreadyBought
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
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
    setSecurityContext()
    def createCartRequest = new CreateCartRequest()
    createCartRequest.setArticles(List.of(1L, 2L, 3L))

    when:
    List<ArticleResponse> old_result = articleController.findBought("NEW_USER")
    cartController.create("NEW_USER", createCartRequest)
    List<ArticleResponse> result = articleController.findBought("NEW_USER").sort((a1, a2) -> a1.getId() <=> a2.getId())

    then:

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
    setSecurityContext()
    def createCartRequest = new CreateCartRequest()
    createCartRequest.setArticles(List.of(1L))

    when:
    List<ArticleResponse> old_result = articleController.findBought("USER_2")
    cartController.create("USER_2", createCartRequest)
    List<ArticleResponse> result = articleController.findBought("USER_2").sort((a1, a2) -> a1.getId() <=> a2.getId())

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
    def createCartRequest = new CreateCartRequest()
    createCartRequest.setArticles(List.of(1L))

    when:
    cartController.create("USER_1", createCartRequest)

    then:
    thrown(ArticleAlreadyBought)
  }


  def setSecurityContext() {
    Jwt jwt = Mock()
    jwt.getClaimAsString("email") >> "pomozimiemail@gmail.com"

    Authentication authentication = Mock()
    authentication.getPrincipal() >> jwt

    SecurityContext securityContext = Mock()
    securityContext.getAuthentication() >> authentication

    SecurityContextHolder.setContext(securityContext)
  }

}
