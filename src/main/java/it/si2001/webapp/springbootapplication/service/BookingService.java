package it.si2001.webapp.springbootapplication.service;

import it.si2001.webapp.springbootapplication.model.Booking;
import it.si2001.webapp.springbootapplication.repository.BookingRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    public BookingService(BookingRepository bookingRepository) { this.bookingRepository = bookingRepository; }

    @Transactional
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }
    @Transactional
    public List<Booking> getBookingsUser(String email){
        return bookingRepository.findBookingByUserEquals(email);
    }
    @Transactional
    public List<Booking> getBookingsUserId(int id){
        return bookingRepository.findBookingByUserId(id);
    }
    @Transactional
    public Optional<Booking> getBooking(int id) throws ResourceNotFoundException { return bookingRepository.findById(id); }
    @Transactional
    public void deleteBooking(int id){
        bookingRepository.deleteById(id);
    }
    @Transactional
    public void saveBooking(Booking booking){
        bookingRepository.save(booking);
    }
}
