package hello.service;

import hello.dao.BookingRepository;
import hello.dao.HostBookingRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Booking;
import hello.model.HostBooking;
import hello.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HostBookingService {
    private HostBookingRepository repository;


    @Autowired
    public HostBookingService(HostBookingRepository repository) {
        this.repository = repository;
    }


    public HostBooking selectHostBookingById(String id) {
        return repository.findById(id).get();
    }

    public List<HostBooking> getAllHostBookings() {
        return repository.findAll();
    }

    public HostBooking addHostBooking(HostBooking booking) {
        HostBooking savedBooking = repository.save(booking);
        return repository.findById(savedBooking.getId()).orElse(null);
    }

    public List<HostBooking> getAllHostBookingsForUser(User user) {
        List<HostBooking> userBookings = new ArrayList<>();
        List<HostBooking> bookings = repository.findAll();
        for (HostBooking booking : bookings) {
            if (booking.getUser().getId().equals(user.getId())) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }



    public boolean deleteHostBookingById(String id) {

        if (!repository.existsById(id)) {
            throw new NotFoundException("id " + id + "  not found");
        }
        repository.deleteById(id);
        return true;
    }

    public HostBooking updateHostBookingById(String id, HostBooking bookingUpdate) {
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


