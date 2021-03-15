package hello.controller;


import hello.model.Booking;
import hello.model.HostBooking;
import hello.model.Role;
import hello.model.User;
import hello.model.UserUtil;
import hello.service.BookingService;
import hello.service.HostBookingService;
import hello.service.RoleService;
import hello.service.SiteUserDetails;
import hello.service.UserService;
import org.apache.catalina.Host;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@Controller
public class UserUIController {
    private UserService userService;
    private BookingService bookingService;
    private HostBookingService hostBookingService;

    @Autowired
    public UserUIController(UserService userService, BookingService bookingService, HostBookingService hostBookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
        this.hostBookingService = hostBookingService;
    }


    static Logger log = Logger.getLogger(UserUIController.class);

    @GetMapping(value = "/account")
    public String getUserAccount(Model model) {
        model.addAttribute("activeLink", "Your Profile");
        User user = userService.findUserByEmail(UserUtil.userName());
        model.addAttribute("user", user);
        if (userService.hasRole("USER_HOST")) {
            model.addAttribute("bookings", hostBookingService.getAllHostBookingsForUser(user));
        }
        if (userService.hasRole("USER_CUSTOMER")) {

            model.addAttribute("bookings", bookingService.getAllBookingsForUser(user));
        }
        return "account";
    }


}
