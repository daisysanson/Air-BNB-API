package hello.service;

import hello.dao.ApartmentRepository;
import hello.dao.BookingRepository;
import hello.dao.CustomerRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.MultiErrorException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment;
import hello.model.Booking;
import hello.model.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;
@Service
public class BookingService {
    private BookingRepository repository;

    @Autowired
    public BookingService(BookingRepository repository){
        this.repository = repository;
    }

    public Booking selectBookingById(String id) {
        Optional<Booking> searchBooking = repository.findById(id);;
        return repository.findById(id).get();
    }


    public List<Booking> getAllBookings() {

        return repository.findAll();
    }


    public Booking addBooking(Booking booking) {
        repository.insert(booking);
        return booking;
    }


    public boolean deleteBookingById(String id) {
        {
            if (!repository.existsById(id)) {
                throw new NotFoundException("id " + id + "  not found");
            }
            repository.deleteById(id);
            return true;

        }
    }

    public Booking updateBookingById(@PathVariable String id, Booking bookingUpdate) {
        if (StringUtils.isBlank(bookingUpdate.getId())) {
            throw new BadRequestException("Please enter an id ");
        }
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");
        }
        if (!repository.existsById(id)) {
            throw new NotFoundException("id " + id + " not found");

        } else

            return repository.save(bookingUpdate);
    }


}




