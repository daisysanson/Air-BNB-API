package hello.controller;

import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Booking;
import hello.model.BookingRequest;
import hello.model.User;
import hello.model.UserUtil;
import hello.service.ApartmentService;
import hello.service.BookingService;
import hello.service.CustomerService;
import hello.service.SiteUserDetails;
import hello.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
public class BookingUIController {

    private BookingService bookingService;
    private ApartmentService apartmentService;
    private CustomerService customerService;
    private UserService userService;

    @Autowired
    public BookingUIController(BookingService bookingService, ApartmentService apartmentService, CustomerService customerService, UserService userService) {
        this.bookingService = bookingService;
        this.apartmentService = apartmentService;
        this.customerService = customerService;
        this.userService = userService;

    }

    static Logger log = Logger.getLogger(BookingUIController.class);


    @GetMapping("/booking")
    public String showBookingLandingPage(Model model) {
        model.addAttribute("activeLink", "Booking");
        model.addAttribute("title", "Bookings!");
        return "booking";
    }


    @GetMapping("/newBookingCreate")
    public String showAddBookingForm(Model model) {
        User user1 = userService.findUserByEmail(UserUtil.userName());
        model.addAttribute("booking", new Booking());
        model.addAttribute("apartments", apartmentService.getAllApartments());
        model.addAttribute("user", user1.getId());
        model.addAttribute("activeLink", "Booking");
        model.addAttribute("title", "Create a New Booking");


        return "newBookingCreate";
    }


    @PostMapping(value = "/newBooking")
    public String showBooking(@ModelAttribute("booking") Booking booking,Model model){
        log.info(UserUtil.userName());

        model.addAttribute("booking", bookingService.addBooking(booking));
        model.addAttribute("activeLink", "Booking");
        model.addAttribute("title", "Success!");

        return "newBooking";
//        } catch (NotFoundException e) {
//            log.info("Customer name not found");
//        }
//        return "notFound";
//    }
    }


    @GetMapping("/getBooking")
    public String showBookingById(Model model) {
        Booking booking = new Booking();
        model.addAttribute("booking", booking);
        model.addAttribute("activeLink", "Booking");
        model.addAttribute("title", "Find a Booking");
        return "getBooking";
    }

    @PostMapping("/getBookingResult")
    public String showFindApartmentResult(@ModelAttribute("booking") Booking booking,
                                          @RequestParam("id") String id,
                                          Model model) {
        try {
            model.addAttribute("booking", bookingService.selectBookingById(id));
            model.addAttribute("title", "Result");
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
        model.addAttribute("title", "Delete a Booking");
        model.addAttribute("activeLink", "Booking");
        return "deleteBookingForm";
    }

    @GetMapping("/deleteBookingResult")
    public String showDeleteBookingForm(@ModelAttribute("booking") Booking booking,
                                        @RequestParam("id") String id, Model model) {
        try {
            model.addAttribute("booking", bookingService.deleteBookingById(id));
            model.addAttribute("activeLink", "Booking");
            model.addAttribute("title", "Success!");
            return "deleteBookingResult";
        } catch (NotFoundException e) {
            return "notFound";
        }
    }


}
