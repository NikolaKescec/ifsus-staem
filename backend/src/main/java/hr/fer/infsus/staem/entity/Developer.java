package hr.fer.infsus.staem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "developer")
@Data
public class Developer {

    @Id
    private Long id;

    private String name;

}
