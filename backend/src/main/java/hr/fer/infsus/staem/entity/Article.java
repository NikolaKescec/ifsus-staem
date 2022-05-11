package hr.fer.infsus.staem.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "article")
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Enumerated(EnumType.STRING)
    private ArticleType articleType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_base_article", foreignKey = @ForeignKey(name = "id_base_article"))
    private List<Article> dlcs;

    @ManyToMany()
    @JoinTable(name = "article_publisher",
        joinColumns = { @JoinColumn(name = "id_article", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_publisher", nullable = false) })
    private Set<Publisher> publishers;

    @ManyToMany
    @JoinTable(name = "article_developer",
        joinColumns = { @JoinColumn(name = "id_article", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_developer", nullable = false) })
    private Set<Developer> developers;

    @ManyToMany
    @JoinTable(name = "article_category",
        joinColumns = { @JoinColumn(name = "id_article", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_category", nullable = false) })
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(name = "article_genre",
        joinColumns = { @JoinColumn(name = "id_article", nullable = false) },
        inverseJoinColumns = { @JoinColumn(name = "id_genre", nullable = false) })
    private Set<Genre> genres;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_article", foreignKey = @ForeignKey(name = "id_article"), nullable = false)
    private List<Picture> pictures;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

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

    public Set<Publisher> getPublishers() {
        if (publishers == null) {
            return Collections.emptySet();
        } else {
            return new LinkedHashSet<>(publishers);
        }
    }

    public void setPublishers(Set<Publisher> publishers) {
        if (this.publishers == null) {
            this.publishers = new LinkedHashSet<>(publishers);
        } else {
            this.publishers.clear();
            this.publishers.addAll(publishers);
        }
    }

    public Set<Developer> getDevelopers() {
        if (developers == null) {
            return Collections.emptySet();
        } else {
            return new LinkedHashSet<>(developers);
        }
    }

    public void setDevelopers(Set<Developer> developers) {
        if (this.developers == null) {
            this.developers = new LinkedHashSet<>(developers);
        } else {
            this.developers.clear();
            this.developers.addAll(developers);
        }
    }

    public Set<Category> getCategories() {
        if (categories == null) {
            return Collections.emptySet();
        } else {
            return new LinkedHashSet<>(categories);
        }
    }

    public void setCategories(Set<Category> categories) {
        if (this.categories == null) {
            this.categories = new LinkedHashSet<>(categories);
        } else {
            this.categories.clear();
            this.categories.addAll(categories);
        }
    }

    public Set<Genre> getGenres() {
        if (genres == null) {
            return Collections.emptySet();
        } else {
            return new LinkedHashSet<>(genres);
        }
    }

    public void setGenres(Set<Genre> genres) {
        if (this.genres == null) {
            this.genres = new LinkedHashSet<>(genres);
        } else {
            this.genres.clear();
            this.genres.addAll(genres);
        }
    }

    public List<Picture> getPictures() {
        if (pictures == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(pictures);
        }
    }

    public void setPictures(List<Picture> pictures) {
        if (this.pictures == null) {
            this.pictures = new ArrayList<>(pictures);
        } else {
            this.pictures.clear();
            this.pictures.addAll(pictures);
        }
    }

}
