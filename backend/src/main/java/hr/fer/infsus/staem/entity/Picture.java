package hr.fer.infsus.staem.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "picture")
@Getter
@Setter
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "text")
    private String urlFull;

    @Lob
    @Column(columnDefinition = "text")
    private String urlThumbnail;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

}
