package it.si2001.webapp.springbootapplication.repository;

import it.si2001.webapp.springbootapplication.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
