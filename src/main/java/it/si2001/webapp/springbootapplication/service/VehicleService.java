package it.si2001.webapp.springbootapplication.service;


import it.si2001.webapp.springbootapplication.model.Vehicle;
import it.si2001.webapp.springbootapplication.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public List<Vehicle> getVehicles(){
        return vehicleRepository.findAll();
    }
    @Transactional
    public Optional<Vehicle> getVehicle(int id){ return vehicleRepository.findById(id); }
    @Transactional
    public void deleteVehicle(int id){
        vehicleRepository.deleteById(id);
    }
    @Transactional
    public void saveVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }
}
