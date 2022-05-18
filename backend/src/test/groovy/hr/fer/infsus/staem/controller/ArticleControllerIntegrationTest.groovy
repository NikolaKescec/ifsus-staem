package hr.fer.infsus.staem.controller

import hr.fer.infsus.staem.controller.response.ArticleDetailsResponse
import hr.fer.infsus.staem.controller.response.ArticleResponse
import hr.fer.infsus.staem.entity.ArticleType
import hr.fer.infsus.staem.repository.query.PriceRange
import hr.fer.infsus.staem.testBuilders.CreateArticleRequestTestBuilder
import hr.fer.infsus.staem.testBuilders.FindArticleQueryTestBuilder
import hr.fer.infsus.staem.testBuilders.UpdateArticleRequestTestBuilder
import hr.fer.infsus.staem.testBuilders.UserInfoTestBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

@Subject(ArticleController.class)
@Transactional
@SpringBootTest
@Sql(["/db/test-schema.sql", "/db/test-data.sql"])
@ActiveProfiles("test")
class ArticleControllerIntegrationTest extends Specification {

  @Autowired
  ArticleController articleController

  def "get all articles"() {
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
    Page<ArticleResponse> result = articleController.findByQuery(query)

    then:
    result.totalElements == 2
    result.totalPages == 1
    verifyAll(result.content.get(0)) {
      it.id == 1
      it.title == "Article 1"
      it.price == 100
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
    }
    verifyAll(result.content.get(1)) {
      it.id == 2
      it.title == "Article 2"
      it.price == 200
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
    }
  }

  def "find non bought article"() {
    given:
    def userInfo = UserInfoTestBuilder.builder().build();
    def articleId = 1L

    when:
    ArticleDetailsResponse result = articleController.findById(userInfo, articleId)

    then:
    verifyAll(result) {
      it.id == articleId
      it.title == "Article 1"
      it.price == 100
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
      it.releaseDate == LocalDate.of(2018, 1, 1)
      it.description == "Description 1"
      it.articleType == "GAME"
      !it.alreadyBought
      categories.stream().map(category -> category.getId()).toList() == [1L]
      developers.stream().map(developers -> developers.getId()).toList() == [1L]
      publishers.stream().map(publishers -> publishers.getId()).toList() == [1L]
      genres.stream().map(genre -> genre.getId()).toList() == [1L]
    }
  }

  def "find bought article"() {
    given:
    def userInfo = UserInfoTestBuilder.builder().def().withSubject('USER_1').build();
    def articleId = 1L

    when:
    ArticleDetailsResponse result = articleController.findById(userInfo, articleId)

    then:
    verifyAll(result) {
      it.id == articleId
      it.title == "Article 1"
      it.price == 100
      it.pictureUrl == 'http://www.google.com'
      it.currency == "USD"
      it.releaseDate == LocalDate.of(2018, 1, 1)
      it.description == "Description 1"
      it.articleType == "GAME"
      it.alreadyBought
      categories.stream().map(category -> category.getId()).toList() == [1L]
      developers.stream().map(developers -> developers.getId()).toList() == [1L]
      publishers.stream().map(publishers -> publishers.getId()).toList() == [1L]
      genres.stream().map(genre -> genre.getId()).toList() == [1L]
    }
  }

  def "find all bought articles from user"() {
    given:
    def userInfo = UserInfoTestBuilder.builder().def().withSubject('USER_1').build();

    when:
    List<ArticleResponse> result = articleController.findBought(userInfo)

    then:
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

  def "create article"() {
    given:
    def createArticleRequest = CreateArticleRequestTestBuilder.builder().def().build()

    when:
    ArticleDetailsResponse result = articleController.create(createArticleRequest)

    then:
    verifyAll(result) {
      it.id == 7
      it.title == "title"
      it.price == 1
      it.pictureUrl == 'pictureUrl'
      it.currency == "USD"
      it.releaseDate == LocalDate.of(2018, 1, 1)
      it.description == "description"
      it.articleType == "GAME"
      !it.alreadyBought
      categories.stream().filter(Objects::nonNull).map(category -> category.getId()).toList() == [1L]
      developers.stream().filter(Objects::nonNull).map(developers -> developers.getId()).toList() == [1L]
      publishers.stream().filter(Objects::nonNull).map(publishers -> publishers.getId()).toList() == [1L]
      genres.stream().filter(Objects::nonNull).map(genre -> genre.getId()).toList() == [1L]
      it.dlcs.size() == 0
    }
  }

  def "update article"() {
    given:
    def updateArticleRequest = UpdateArticleRequestTestBuilder.builder().def()
        .withArticleType(ArticleType.DLC)
        .withBaseArticleId(2L)
        .withTitle("new title")
        .withPrice(2.0)
        .withDevelopers(List.of(2L))
        .withCategories(List.of(2L))
        .withPublishers(List.of(2L))
        .withGenres(List.of(2L))
        .build()

    when:
    ArticleDetailsResponse result = articleController.update(1L, updateArticleRequest)

    then:
    verifyAll(result) {
      it.id == 1L
      it.title == "new title"
      it.price == 2.0
      it.pictureUrl == 'pictureUrl'
      it.currency == "USD"
      it.releaseDate == LocalDate.of(2018, 1, 1)
      it.description == "description"
      it.articleType == "DLC"
      !it.alreadyBought
      categories.stream().filter(Objects::nonNull).map(category -> category.getId()).toList() == [2L]
      developers.stream().filter(Objects::nonNull).map(developers -> developers.getId()).toList() == [2L]
      publishers.stream().filter(Objects::nonNull).map(publishers -> publishers.getId()).toList() == [2L]
      genres.stream().filter(Objects::nonNull).map(genre -> genre.getId()).toList() == [2L]
      it.dlcs.size() == 0
    }
  }

}
