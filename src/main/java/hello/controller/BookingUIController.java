package hello.controller;

import hello.exceptions.BadRequestException;
import hello.model.Apartment;
import hello.model.Booking;
import hello.model.Customer;
import hello.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingUIController {
    private BookingService bookingService;

    @Autowired
    public BookingUIController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping("/booking")
    public String getCustomerId(Model model) {
        return "booking";
    }



    @GetMapping("/newBookingcreate")
    public String showAddForm(Model model) {
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        return "newBookingcreate";
    }


    @PostMapping("/newBooking")
    public String addCustomer(@ModelAttribute("booking") Booking booking,
                              @RequestParam("apartment") Apartment apartment,
                              @RequestParam("customer") Customer customer,

                              Model model) {
        try {
            Booking b = bookingService.addBooking(booking);
            model.addAttribute("booking", booking);

            return "newBooking";
        } catch (BadRequestException e) {
            return "badRequest";
        }
    }


}
