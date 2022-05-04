package hr.fer.infsus.staem.entity;

import hr.fer.infsus.staem.entity.composite.PurchasedArticlesId;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "purchased_articles")
@IdClass(PurchasedArticlesId.class)
public class PurchasedArticles {

    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "id_user"))
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "id_article"))
    private Article article;

    private LocalDate dateOfPurchase;

    private Double price;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
