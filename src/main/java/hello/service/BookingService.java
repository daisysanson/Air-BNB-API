package hello.service;

import hello.dao.BookingRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Booking;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private BookingRepository repository;

    @Autowired
    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking selectBookingById(String id) {
        return repository.findById(id).get();
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    public Booking addBooking(Booking booking) {
        Booking savedBooking = repository.save(booking);
        return repository.findById(savedBooking.getId()).orElse(null);
    }



    public boolean deleteBookingById(String id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("id " + id + "  not found");
        }
        repository.deleteById(id);
        return true;
    }

    public Booking updateBookingById(String id, Booking bookingUpdate) {
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");
        }

        if (StringUtils.isBlank(bookingUpdate.getId())) {
            throw new BadRequestException("Please enter an id ");
        }

        if (!repository.existsById(id)) {
            throw new NotFoundException("id " + id + " not found");
        }
        return repository.save(bookingUpdate);
    }
}
