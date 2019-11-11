package it.si2001.webapp.springbootapplication.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idCategory;

    @Column(name="category", nullable = false, unique = true, columnDefinition = "varchar(45)")
    private String category;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    @JsonManagedReference
    private Set<Vehicle> vehicles;

}
