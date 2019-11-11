package it.si2001.webapp.springbootapplication.dto;

import it.si2001.webapp.springbootapplication.model.Booking;
import it.si2001.webapp.springbootapplication.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO convertToDTO(Booking booking);

    List<BookingDTO> convertToListDTO(List<Booking> bookings);

    Booking convertToModel(BookingDTO bookingDTO);
}
