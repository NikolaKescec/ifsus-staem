package hr.fer.infsus.staem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate purchaseDate;

    @OneToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "id_user"), nullable = false)
    private Users user;

    @ManyToMany
    @JoinTable(name = "article_cart",
        joinColumns = { @JoinColumn(name = "id_cart", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_article", nullable = false) })
    private Set<Article> articles;

    public Set<Article> getArticles() {
        if (articles == null) {
            return Collections.emptySet();
        } else {
            return new LinkedHashSet<>(articles);
        }
    }

    public void setArticles(Set<Article> articles) {
        if (this.articles == null) {
            this.articles = new LinkedHashSet<>(articles);
        } else {
            this.articles.clear();
            this.articles.addAll(articles);
        }
    }

}
