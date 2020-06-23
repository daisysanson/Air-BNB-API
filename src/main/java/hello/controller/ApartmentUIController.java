package hello.controller;

import hello.exceptions.BadRequestException;
import hello.exceptions.MultiErrorException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment2;
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


    @GetMapping("/apartment")
    public String getApartmentLandingPage(Model model) {
        model.addAttribute("activeLink", "Apartment");
        return "apartment";
    }

    @GetMapping("/findAnApartment")
    public String showFindApartmentForm(Model model) {
        Apartment2 apartment = new Apartment2();
        model.addAttribute("apartment", apartment);
        model.addAttribute("activeLink", "Apartment");
        return "findAnApartment";
    }

    @PostMapping("/findApartmentResult")
    public String showFindApartmentResult(@ModelAttribute("apartment") Apartment2 apartment, @RequestParam("id") String id, Model model) {
        if (apartmentService == null) {
            log.info("no apartment input model");
            return "badRequest";
        }
        try {
            model.addAttribute("activeLink", "Apartment");
            model.addAttribute("apartment", apartmentService.selectApartmentById(id));
        } catch (BadRequestException e) {
            log.info("title field is empty");
            return "badRequest";
        } catch (NotFoundException e) {
            log.info("apartment with id" + id + " not found");
            return "notFound";
        }
        return "findApartmentResult";
    }


    @GetMapping("/showAllApartments")
    public String showAllApartments(Model model) {
        model.addAttribute("apartment", apartmentService.getAllApartments());
        model.addAttribute("activeLink", "Apartment");
        return "showAllApartments";
    }

    @GetMapping("/addApartment")
    public String showAddForm(Model model) {
        Apartment2 apartment = new Apartment2();
        model.addAttribute("apartment", apartment);
        return "addApartment";
    }


    @PostMapping("/apartmentResult")
    public String showAddCustomerForm(@ModelAttribute("apartment") Apartment2 apartment,
                              @RequestParam("title") String title,
                              @RequestParam("address") String address,
                              @RequestParam("guestCapacity") int guestCapacity,
                              @RequestParam("rating") int rating,
                              @RequestParam("rooms") int rooms, Model model) {
        try {
            model.addAttribute("apartment", apartmentService.addApartment(apartment));
            return "apartmentResult";
        } catch (BadRequestException e) {
            log.info("title field is empty");
            return "badRequest";
        }
    }



    @GetMapping("/deleteApartmentForm")
    public String showDeleteApartmentForm(Model model) {
        Apartment2 apartment = new Apartment2();
        model.addAttribute("apartment", apartment);
        model.addAttribute("activeLink", "Apartment");
        return "deleteApartmentForm";
    }

    @GetMapping("/deleteApartmentResult")
    public String showDeleteApartmentForm(@ModelAttribute("apartment") Apartment2 apartment,
                                  @RequestParam("id") String id, Model model) {
        try {
            model.addAttribute("customer", apartmentService.deleteApartmentById(id));
            model.addAttribute("activeLink", "Apartment");
            return "deleteApartmentResult";
        } catch (NotFoundException e) {
            log.info("apartment with id" + id + "not found");
            return "notFound";
        }
    }

    @GetMapping("/updateApartmentForm")
    public String showUpdateForm(Model model) {
        Apartment2 apartment = new Apartment2();
        model.addAttribute("apartment", apartment);
        model.addAttribute("activeLink", "Apartment");
        return "updateApartmentForm";
    }

    @GetMapping("/updateApartmentResult")
    public String showUpdateCustomerForm(@ModelAttribute("apartment") Apartment2 apartmentToUpdate,
                                 @RequestParam("id") String id, Model model) {

        try {
            model.addAttribute("apartment", apartmentService.updateApartmentById(id, apartmentToUpdate));
            model.addAttribute("activeLink", "Apartment");
            return "updateApartmentResult";
        } catch (BadRequestException e) {
            log.info("apartment with id " + id + "not found");
            return "badRequest";
        } catch (NotFoundException e) {
            return "notFound";
        }
    }

}


