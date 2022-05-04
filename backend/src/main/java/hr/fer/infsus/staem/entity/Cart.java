package hr.fer.infsus.staem.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private Long id;

    private LocalDate purchaseDate;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "id_user"), nullable = false)
    private Users user;

    @ManyToMany
    @JoinTable(name = "article_cart",
        joinColumns = { @JoinColumn(name = "id_cart", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_article", nullable = false) })
    private List<Article> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Article> getArticles() {
        if (articles == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(articles);
        }
    }

    public void setArticles(List<Article> articles) {
        if (this.articles == null) {
            this.articles = new ArrayList<>(articles);
        } else {
            this.articles.clear();
            this.articles.addAll(articles);
        }
    }

}
