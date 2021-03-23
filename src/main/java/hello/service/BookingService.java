package hello.service;

import hello.dao.ApartmentRepository;
import hello.dao.BookingRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.ForbiddenException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment;
import hello.model.Booking;
import hello.model.HostBooking;
import hello.model.User;
import hello.model.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private BookingRepository repository;
    private UserService userService;


    @Autowired
    public BookingService(BookingRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }



    public Booking selectBookingById(String id) {
        User user = userService.findUserByEmail(UserUtil.userName());
        if (!checkUsersBooking(id,user)){
            throw new ForbiddenException("This is not your booking!");
        }
        return repository.findById(id).get();
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }

    public Booking addBooking(Booking booking) {
        Booking savedBooking = repository.save(booking);
            return repository.findById(savedBooking.getId()).orElse(null);
        }

    public List<Booking> getAllBookingsForUser(User user){
        List<Booking> userBookings = new ArrayList<>();
        List<Booking> bookings = repository.findAll();
        for (Booking booking : bookings) {
            if (booking.getUser().getId().equals(user.getId())) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }


    public boolean checkUsersBooking(String id, User user) {
        List<Booking> userBookings = getAllBookingsForUser(user);
        for (Booking booking : userBookings) {
            if (booking.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    public boolean deleteBookingById(String id) {
        User user = userService.findUserByEmail(UserUtil.userName());

        if (!repository.existsById(id)) {
            throw new NotFoundException("id " + id + "  not found");
        }
        if (!checkUsersBooking(id,user)){
            throw new ForbiddenException("This is not your booking!");
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
