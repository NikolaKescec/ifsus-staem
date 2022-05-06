package hr.fer.infsus.staem.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "picture")
@Getter
@Setter
public class Picture {

    @Id
    @GeneratedValue(generator = "max-generator")
    @GenericGenerator(name = "max-generator", strategy = "hr.fer.infsus.staem.generator.StaemEntityIdGenerator")
    private Long id;

    @Lob
    @Column(columnDefinition = "text")
    private String urlFull;

    @Lob
    @Column(columnDefinition = "text")
    private String urlThumbnail;

}
