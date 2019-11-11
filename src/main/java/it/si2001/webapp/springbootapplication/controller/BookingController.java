package it.si2001.webapp.springbootapplication.controller;

import it.si2001.webapp.springbootapplication.dto.BookingDTO;
import it.si2001.webapp.springbootapplication.dto.BookingMapper;
import it.si2001.webapp.springbootapplication.model.Booking;
import it.si2001.webapp.springbootapplication.service.BookingService;
import it.si2001.webapp.springbootapplication.service.UserService;
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
@RequestMapping( value = "api/booking")
public class BookingController {

    private final UserService userService;
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final VehicleService vehicleService;


    public BookingController(UserService userService, BookingService bookingService, BookingMapper bookingMapper, VehicleService vehicleService) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
        this.vehicleService = vehicleService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BookingDTO>> findAll() {
        return ResponseEntity.ok(this.bookingMapper.convertToListDTO(this.bookingService.getBookings()));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<BookingDTO> findById(@PathVariable int id) {
        Optional<Booking> booking = this.bookingService.getBooking(id);
        return ResponseEntity.ok(this.bookingMapper.convertToDTO(booking.orElse(null)));
    }

    @PostMapping
    public ResponseEntity<BookingDTO> create(@RequestBody BookingDTO bookingDTO) throws ParseException {
        this.bookingService.saveBooking(bookingMapper.convertToModel(bookingDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<BookingDTO> update(@PathVariable int id, @RequestBody BookingDTO bookingDTO) throws ParseException {
        Optional<Booking> booking = this.bookingService.getBooking(id);
        if(booking.isPresent()) {
            this.bookingService.saveBooking(booking.get());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(bookingDTO);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Booking> booking = this.bookingService.getBooking(id);
        if(booking.isPresent()) {
            this.bookingService.deleteBooking(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(booking.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("booking not found with " + id + " ID");
    }
}
