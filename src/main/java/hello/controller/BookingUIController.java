package hello.controller;

import hello.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
