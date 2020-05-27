package hello.controller;


import com.sun.corba.se.spi.ior.ObjectKey;
import hello.dao.BookingRepository;
import hello.model.Apartment;
import hello.model.Booking;
import hello.model.Customer;
import hello.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RequestMapping(value = "/api/v1/bookings")
@RestController
public class BookingController {
    private BookingRepository repository;

    @Autowired
    public BookingController(BookingRepository repository) {
        this.repository = repository;
    }


    @GetMapping(value = "/")
    public ResponseEntity<Object> getAllBookings() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/id")
    public ResponseEntity<Object> getBookingById(@PathVariable("id") String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> addBooking(@RequestBody Booking booking) {
        Booking saveBooking = repository.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBooking);
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity<Object>deleteBooking(@RequestBody String id) {
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");

    }
    @PutMapping(value = "/id")
        public ResponseEntity<Object> updateBooking (@RequestBody String id, Booking bookingUpdate){
        BookingService bookingService = new BookingService(); 
        Booking booking = bookingService.updateBookingById(id, bookingUpdate);

        return ResponseEntity.status(HttpStatus.OK).body("booking id" + id + "has been updated");
    }
}





