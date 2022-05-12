package hr.fer.infsus.staem.entity.composite;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class PurchasedArticlesId implements Serializable {

    private String user;

    private Long article;

    public PurchasedArticlesId(String id_user, Long id_article) {
        this.user = id_user;
        this.article = id_article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PurchasedArticlesId that = (PurchasedArticlesId) o;
        return Objects.equals(user, that.user) && Objects.equals(article, that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, article);
    }

}
