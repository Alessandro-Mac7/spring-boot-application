package it.si2001.webapp.springbootapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idBooking;

    @Column(name="start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name="end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @JsonBackReference
    private Vehicle vehicle;

}
