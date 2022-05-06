package hr.fer.infsus.staem.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(generator = "max-generator")
    @GenericGenerator(name = "max-generator", strategy = "hr.fer.infsus.staem.generator.StaemEntityIdGenerator")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Article> articles;

    public void removeArticle(Article article) {
        articles.remove(article);
    }

}