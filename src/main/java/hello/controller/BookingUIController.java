package hello.controller;

import hello.dao.BookingRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment2;
import hello.model.Booking;
import hello.model.Customer;
import hello.service.ApartmentService;
import hello.service.BookingService;
import hello.service.CustomerService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingUIController {

    private BookingService bookingService;
    private ApartmentService apartmentService;
    private Apartment2 apartment2;


    @Autowired
    public BookingUIController(BookingService bookingService, ApartmentService apartmentService) {
        this.bookingService = bookingService;
        this.apartmentService=apartmentService;
    }


    @GetMapping("/booking")
    public String getBookingPage(Model model) {
        model.addAttribute("activeLink", "Booking");
        return "booking";
    }


    @GetMapping("/newBookingCreate")
    public String showAddForm(Model model) {
        Booking booking = new Booking();
        model.addAttribute("apartments",  apartmentService.getAllApartments());
        model.addAttribute("booking", booking);

        return "newBookingCreate";
    }

    @PostMapping("/newBooking")
    public String addBooking(@ModelAttribute("booking") Booking booking,  Model model) {
        try {
            Booking b = bookingService.addBooking(booking);
            model.addAttribute("booking", b);
            return "newBooking";
        } catch (BadRequestException e) {
            return "badRequest";
        }
    }


    @GetMapping("/viewBooking")
    public String viewBookingById(Model model){
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        return "viewBooking";
    }

    @PostMapping("/viewBookingResult")
    public String showFindApartmentResult(@ModelAttribute("booking") Booking booking,
                                          @RequestParam("id") String id,
                                          Model model) {
        if (bookingService == null) {
            return "badRequest";
        }
        try {
            model.addAttribute("booking", bookingService.selectBookingById(id));
        } catch (BadRequestException e) {
            return "badRequest";
        } catch (NotFoundException e) {
            return "notFound";
        }
        return "viewBookingResult";
    }



}
