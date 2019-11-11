package it.si2001.webapp.springbootapplication.controller;

import it.si2001.webapp.springbootapplication.dto.VehicleDTO;
import it.si2001.webapp.springbootapplication.dto.VehicleMapper;
import it.si2001.webapp.springbootapplication.model.Vehicle;
import it.si2001.webapp.springbootapplication.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Slf4j

@RestController
@RequestMapping( value = "api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    public VehicleController(VehicleService vehicleService, VehicleMapper vehicleMapper) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<VehicleDTO>> findAll() {
        return ResponseEntity.ok(this.vehicleMapper.convertToListDTO(this.vehicleService.getVehicles()));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<VehicleDTO> findById(@PathVariable int id) {
        Optional<Vehicle> vehicle = this.vehicleService.getVehicle(id);
        return ResponseEntity.ok(this.vehicleMapper.convertToDTO(vehicle.orElse(null)));
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> create(@RequestBody VehicleDTO vehicleDTO) throws ParseException {
        this.vehicleService.saveVehicle(vehicleMapper.convertToModel(vehicleDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleDTO);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<VehicleDTO> update(@PathVariable int id, @RequestBody VehicleDTO vehicleDTO) throws ParseException {
        Optional<Vehicle> vehicle = this.vehicleService.getVehicle(id);
        if(vehicle.isPresent()) {
            this.vehicleService.saveVehicle(vehicle.get());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehicleDTO);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Vehicle> vehicle = this.vehicleService.getVehicle(id);
        if(vehicle.isPresent()) {
            this.vehicleService.deleteVehicle(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehicle.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("vehicle not found user with " + id + " ID");
    }
}
