package hr.fer.infsus.staem.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@SQLDelete(sql = "update Users set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
@Getter
@Setter
public class Users {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "id_role"))
    private Role role;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

}
