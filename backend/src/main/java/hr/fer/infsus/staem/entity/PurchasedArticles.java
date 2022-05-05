package hr.fer.infsus.staem.entity;

import hr.fer.infsus.staem.entity.composite.PurchasedArticlesId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchased_articles")
@IdClass(PurchasedArticlesId.class)
@Data
public class PurchasedArticles {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "id_user"), nullable = false)
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_article", foreignKey = @ForeignKey(name = "id_article"), nullable = false)
    private Article article;

    private LocalDate dateOfPurchase;

    private BigDecimal price;

}
