package hello.controller;

import hello.dao.BookingRepository;
import hello.dao.HostBookingRepository;
import hello.model.Apartment;
import hello.model.Booking;
import hello.model.HostBooking;
import hello.service.BookingService;
import hello.service.HostBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RequestMapping(value = "/api/v1/hostbookings")
    @RestController
    public class HostBookingController {
        private HostBookingRepository repository;
        private HostBookingService bookingService;


        @Autowired
        public HostBookingController(HostBookingRepository repository, HostBookingService bookingService) {
            this.repository = repository;
            this.bookingService = bookingService;
        }


        @GetMapping(value = "/")
        public ResponseEntity<Object> getAllHostBookings() {
            return ResponseEntity.ok(repository.findAll());
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<Object> getHostBookingById(@PathVariable("id") String id) {
            return ResponseEntity.ok(repository.findById(id));
        }


        @PostMapping(value = "/")
        public ResponseEntity<Object> addHostBooking(@RequestBody HostBooking booking) {
            HostBooking saveBooking = bookingService.addHostBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveBooking);
        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Object> deleteHostBooking(@PathVariable("id") String id) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("deleted");

        }

        @PutMapping(value = "/{id}")
        public ResponseEntity updateHostBooking(@PathVariable("id") String id, @RequestBody HostBooking bookingUpdate) {
            HostBooking booking = bookingService.updateHostBookingById(id, bookingUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("booking id" + id + "has been updated");
        }
    }


