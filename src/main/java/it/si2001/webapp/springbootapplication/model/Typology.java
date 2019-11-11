package it.si2001.webapp.springbootapplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "typology")
@Data
public class Typology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idTypology;

    @Column(name="type", nullable = false, unique = true, columnDefinition = "varchar(45)")
    private String type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typology" )
    @JsonManagedReference
    private Set<User> user;

}
