package it.si2001.webapp.springbootapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(name="name", nullable = false, columnDefinition = "varchar(45)")
    private String name;

    @Column(name="last_name", nullable = false, columnDefinition = "varchar(45)")
    private String lastName;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name="email", nullable = false, unique = true, columnDefinition = "varchar(45)")
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private Set<Booking> bookings;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "typology_id", referencedColumnName = "id")
    @JsonBackReference
    private Typology typology;

}
