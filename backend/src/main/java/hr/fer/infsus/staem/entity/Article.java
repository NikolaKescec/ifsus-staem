package hr.fer.infsus.staem.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "article")
@Data
public class Article {

    @Id
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String description;

    private BigDecimal price;

    @Column(length = 3)
    private String currency;

    @Lob
    @Column(columnDefinition = "text")
    private String pictureUrl;

    private LocalDate releaseDate;

    @Column(length = 10)
    private String articleType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_base_article", foreignKey = @ForeignKey(name = "id_base_article"))
    private List<Article> dlcs;

    @ManyToMany()
    @JoinTable(name = "article_publisher",
        joinColumns = { @JoinColumn(name = "id_article", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_publisher", nullable = false) })
    private List<Publisher> publishers;

    @ManyToMany
    @JoinTable(name = "article_developer",
        joinColumns = { @JoinColumn(name = "id_article", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_developer", nullable = false) })
    private List<Developer> developers;

    @ManyToMany
    @JoinTable(name = "article_category",
        joinColumns = { @JoinColumn(name = "id_article", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_category", nullable = false) })
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "article_genre",
        joinColumns = { @JoinColumn(name = "id_article", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_genre", nullable = false) })
    private List<Genre> genres;

    public List<Article> getDlcs() {
        if (dlcs == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(dlcs);
        }
    }

    public void setDlcs(List<Article> dlcContent) {
        if (this.dlcs == null) {
            this.dlcs = new ArrayList<>(dlcContent);
        } else {
            this.dlcs.clear();
            this.dlcs.addAll(dlcContent);
        }
    }

    public List<Publisher> getPublishers() {
        if (publishers == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(publishers);
        }
    }

    public void setPublishers(List<Publisher> publishers) {
        if (this.publishers == null) {
            this.publishers = new ArrayList<>(publishers);
        } else {
            this.publishers.clear();
            this.publishers.addAll(publishers);
        }
    }

    public List<Developer> getDevelopers() {
        if (developers == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(developers);
        }
    }

    public void setDevelopers(List<Developer> developers) {
        if (this.developers == null) {
            this.developers = new ArrayList<>(developers);
        } else {
            this.developers.clear();
            this.developers.addAll(developers);
        }
    }

    public List<Category> getCategories() {
        if (categories == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(categories);
        }
    }

    public void setCategories(List<Category> categories) {
        if (this.categories == null) {
            this.categories = new ArrayList<>(categories);
        } else {
            this.categories.clear();
            this.categories.addAll(categories);
        }
    }

    public List<Genre> getGenres() {
        if (genres == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(genres);
        }
    }

    public void setGenres(List<Genre> genres) {
        if (this.genres == null) {
            this.genres = new ArrayList<>(genres);
        } else {
            this.genres.clear();
            this.genres.addAll(genres);
        }
    }

}
