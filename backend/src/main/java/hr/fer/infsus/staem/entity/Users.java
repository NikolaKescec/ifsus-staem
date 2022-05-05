package hr.fer.infsus.staem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class Users {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "id_role"))
    private Role role;

}
