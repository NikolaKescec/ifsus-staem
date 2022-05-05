package hr.fer.infsus.staem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publisher")
@Data
public class Publisher {

    @Id
    private Long id;

    private String name;

}
