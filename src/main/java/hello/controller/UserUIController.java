package hello.controller;


import hello.model.Booking;
import hello.model.User;
import hello.model.UserUtil;
import hello.service.BookingService;
import hello.service.SiteUserDetails;
import hello.service.UserService;
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

@Controller
public class UserUIController {
    private UserService userService;
    private BookingService bookingService;

    @Autowired
    public UserUIController(UserService userService, BookingService bookingService) {
        this.userService = userService;
        this.bookingService = bookingService;
    }


    static Logger log = Logger.getLogger(UserUIController.class);

    @GetMapping(value = "/account")
    public String getUserAccount(Model model){
        model.addAttribute("activeLink", "Your Profile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user1 = userService.findUserByEmail(UserUtil.userName());
        model.addAttribute("user", user1);
       model.addAttribute("bookings", bookingService.getAllBookingsForUser(user1));
        return "account";
    }

//    @GetMapping("/account")
//    public String viewUserAccountForm(
//            @AuthenticationPrincipal SiteUserDetails userDetails,
//            Model model) {
//        String userEmail = userDetails.getUsername();
//        User user = userService.findUserByEmail(userEmail);
//
//        model.addAttribute("user", user);
//        model.addAttribute("pageTitle", "Account Details");
//
//        return "users/account_form";
//    }

}
