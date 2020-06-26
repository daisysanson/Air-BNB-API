package hello.controller;

import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Booking;
import hello.model.BookingRequest;
import hello.model.Customer;
import hello.service.ApartmentService;
import hello.service.BookingService;
import hello.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BookingUIController {

    private BookingService bookingService;
    private ApartmentService apartmentService;
    private CustomerService customerService;


    @Autowired
    public BookingUIController(BookingService bookingService, ApartmentService apartmentService, CustomerService customerService) {
        this.bookingService = bookingService;
        this.apartmentService = apartmentService;
        this.customerService = customerService;


    }

    static Logger log = Logger.getLogger(CustomerController.class);


    @GetMapping("/booking")
    public String showBookingLandingPage(Model model) {
        model.addAttribute("activeLink", "Booking");
        return "booking";
    }


    @GetMapping("/newBookingCreate")
    public String showAddBookingForm(Model model) {
        BookingRequest booking = new BookingRequest();
        model.addAttribute("apartments", apartmentService.getAllApartments());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("activeLink", "Booking");
        model.addAttribute("booking", booking);

        return "newBookingCreate";
    }


    @PostMapping(value = "/newBooking")
    public String showBooking(@ModelAttribute("booking") BookingRequest booking, Model model) {
        try {
            List<Customer> c = customerService.findByName(booking.name);
            if (c.isEmpty()) {
                log.info("Customer name does not exist");
                return "badRequest";
            }

            Booking newBooking = new Booking(c.get(0), booking.apartment);

            Booking b = bookingService.addBooking(newBooking);
            model.addAttribute("booking", b);
            model.addAttribute("name", c);
            model.addAttribute("activeLink", "Booking");

            return "newBooking";
        } catch (NotFoundException e) {
            log.info("Customer name not found");
        }
        return "notFound";
    }


    @GetMapping("/getBooking")
    public String showBookingById(Model model) {
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        model.addAttribute("activeLink", "Booking");
        return "getBooking";
    }

    @PostMapping("/getBookingResult")
    public String showFindApartmentResult(@ModelAttribute("booking") Booking booking,
                                          @RequestParam("id") String id,
                                          Model model) {
        try {
            model.addAttribute("booking", bookingService.selectBookingById(id));
            model.addAttribute("activeLink", "Booking");
        } catch (BadRequestException e) {
            return "badRequest";
        } catch (NotFoundException e) {
            return "notFound";
        } catch (NoSuchElementException e) {
            log.info("no booking id entered or does not exist");
            return "badRequest";
        }
        return "getBookingResult";
    }


    @GetMapping("/deleteBookingForm")
    public String showDeleteBookingForm(Model model) {
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        model.addAttribute("activeLink", "Booking");
        return "deleteBookingForm";
    }

    @GetMapping("/deleteBookingResult")
    public String showDeleteBookingForm(@ModelAttribute("booking") Booking booking,
                                        @RequestParam("id") String id, Model model) {
        try {
            model.addAttribute("booking", bookingService.deleteBookingById(id));
            model.addAttribute("activeLink", "Booking");
            return "deleteBookingResult";
        } catch (NotFoundException e) {
            return "notFound";
        }
    }


}
