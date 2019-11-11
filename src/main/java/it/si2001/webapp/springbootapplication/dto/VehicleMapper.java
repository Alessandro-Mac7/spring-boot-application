package it.si2001.webapp.springbootapplication.dto;

import it.si2001.webapp.springbootapplication.model.Vehicle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDTO convertToDTO(Vehicle vehicle);

    List<VehicleDTO> convertToListDTO(List<Vehicle> vehicles);

    Vehicle convertToModel(VehicleDTO vehicleDTO);
}
