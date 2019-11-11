package it.si2001.webapp.springbootapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idVehicle;

    @Column(name="unique_id", nullable = false, unique = true, columnDefinition = "varchar(45)")
    private String uniqueId;

    @Column(name="manufacturer", nullable = false, columnDefinition = "varchar(45)")
    private String manufacturer;

    @Column(name="model", nullable = false, columnDefinition = "varchar(45)")
    private String model;

    @Column(name="car_registration", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date carRegistration;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    private Category category;

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "vehicle")
    @JsonManagedReference
    private Set<Booking> bookings;

}
