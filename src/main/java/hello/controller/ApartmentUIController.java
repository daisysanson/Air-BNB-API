package hello.controller;

import hello.exceptions.MultiErrorException;
import hello.model.Apartment;
import hello.model.Customer;
import hello.service.ApartmentService;
import hello.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ApartmentUIController {
    private ApartmentService apartmentService;


    @Autowired
    public ApartmentUIController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    static Logger log = Logger.getLogger(CustomerController.class);


    @GetMapping("/addApartment")
    public String showAddForm(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        return "addApartment";
    }


    @PostMapping("/apartmentResult")
    public String addCustomer(@ModelAttribute("apartment") Apartment apartment,
                              @RequestParam("title") String title,
                              @RequestParam("location") String location,
//                              @RequestParam("occupiedStartDate") Date occupiedStartDate,
//                              @RequestParam("occupiedEndDate") Date occupiedEndDate,
                              @RequestParam("guestCapacity") int guestCapacity, Model model){


        model.addAttribute("apartment", apartmentService.addApartment(apartment));
        return "apartmentResult";
        }



    }



