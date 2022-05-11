package hr.fer.infsus.staem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "developer")
@Getter
@Setter
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "developers")
    private Set<Article> articles;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public void removeArticle(Article article) {
        articles.remove(article);
    }

}
