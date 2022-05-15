package hr.fer.infsus.staem.service

import hr.fer.infsus.staem.entity.Article
import hr.fer.infsus.staem.exception.EntityNotFoundException
import hr.fer.infsus.staem.mapper.core.CreateMapper
import hr.fer.infsus.staem.mapper.core.UpdateMapper
import hr.fer.infsus.staem.repository.ArticleRepository
import hr.fer.infsus.staem.service.command.create.CreateArticleCommand
import hr.fer.infsus.staem.service.command.update.UpdateArticleCommand
import hr.fer.infsus.staem.service.impl.ArticleCommandServiceImpl
import hr.fer.infsus.staem.testBuilders.ArticleTestBuilder
import hr.fer.infsus.staem.testBuilders.CreateArticleCommandTestBuilder
import hr.fer.infsus.staem.testBuilders.UpdateArticleCommandTestBuilder
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

@Subject(ArticleCommandService)
@ActiveProfiles("test")
class ArticleCommandServiceTest extends Specification {

  ArticleRepository articleRepository = Mock();

  ArticleQueryService articleQueryService = Mock();

  CreateMapper<CreateArticleCommand, Article> createArticleCommandArticleCreateMapper = Mock();

  UpdateMapper<UpdateArticleCommand, Article> updateArticleCommandArticleCreateMapper = Mock();

  def articleCommandService = new ArticleCommandServiceImpl(articleRepository, articleQueryService, createArticleCommandArticleCreateMapper, updateArticleCommandArticleCreateMapper);

  def "should create new article"() {
    given:
    def createCommandDto = CreateArticleCommandTestBuilder.builder().def().build()

    when: "create article"
    Article result = articleCommandService.create(createCommandDto)

    then:
    1 * createArticleCommandArticleCreateMapper.map(createCommandDto) >> ArticleTestBuilder.builder().def().fromCommand(createCommandDto).build()
    1 * articleRepository.save(_ as Article) >> ArticleTestBuilder.builder().def().withId(1L).build()
    verifyAll(result) {
      articleType == createCommandDto.articleType
      title == createCommandDto.title
      currency == createCommandDto.currency
      price == createCommandDto.price
      description == createCommandDto.description
      getCategories().stream().map(category -> category.getId()).toList() == createCommandDto.categories
      getDevelopers().stream().map(developer -> developer.getId()).toList() == createCommandDto.developers
      getPublishers().stream().map(publisher -> publisher.getId()).toList() == createCommandDto.publishers
      getGenres().stream().map(genre -> genre.getId()).toList() == createCommandDto.genres
      pictureUrl == createCommandDto.pictureUrl
      releaseDate == createCommandDto.releaseDate
    }
  }

  def "should update article"() {
    given:
    def updateArticleCommand = UpdateArticleCommandTestBuilder.builder().def().withTitle("updated article").build()

    when: "update article"
    Article result = articleCommandService.update(updateArticleCommand)

    then:
    1 * articleQueryService.findById(updateArticleCommand.id) >> ArticleTestBuilder.builder().def().withId(updateArticleCommand.id).build()
    1 * updateArticleCommandArticleCreateMapper.map(updateArticleCommand, _ as Article)
    1 * articleRepository.save(_ as Article) >> ArticleTestBuilder.builder().def().fromCommand(updateArticleCommand).build()
    verifyAll(result) {
      articleType == updateArticleCommand.articleType
      title == updateArticleCommand.title
      currency == updateArticleCommand.currency
      price == updateArticleCommand.price
      description == updateArticleCommand.description
      getCategories().stream().map(category -> category.getId()).toList() == updateArticleCommand.categories
      getDevelopers().stream().map(developer -> developer.getId()).toList() == updateArticleCommand.developers
      getPublishers().stream().map(publisher -> publisher.getId()).toList() == updateArticleCommand.publishers
      getGenres().stream().map(genre -> genre.getId()).toList() == updateArticleCommand.genres
      pictureUrl == updateArticleCommand.pictureUrl
      releaseDate == updateArticleCommand.releaseDate
    }
  }

  def "should delete article"() {
    given:
    def articleId = 1L

    when: "query for entity"
    articleCommandService.delete(articleId)
    articleQueryService.findById(articleId)

    then:
    2 * getArticleQueryService().findById(articleId) >>>
        ArticleTestBuilder.builder().def().build() >> { throw new EntityNotFoundException(Article.class, new String[]{1L}) }
    1 * articleRepository.delete(_ as Article)
    thrown(EntityNotFoundException)
  }


}
