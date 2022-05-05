package hr.fer.infsus.staem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    private Long id;

    private String name;

}
