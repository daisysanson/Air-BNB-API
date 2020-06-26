package hello.controller;

import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment;
import hello.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ApartmentUIController {
    private ApartmentService apartmentService;


    @Autowired
    public ApartmentUIController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }


    @GetMapping("/apartment")
    public String getApartmentLandingPage(Model model) {
        model.addAttribute("activeLink", "Apartment");
        return "apartment";
    }

    @GetMapping("/findAnApartment")
    public String showFindApartmentForm(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("activeLink", "Apartment");
        return "findAnApartment";
    }

    @PostMapping("/findApartmentResult")
    public String showFindApartmentResult(@ModelAttribute("apartment") Apartment apartment, @RequestParam("id") String id, Model model) {
        try {
            model.addAttribute("activeLink", "Apartment");
            model.addAttribute("apartment", apartmentService.selectApartmentById(id));
        } catch (BadRequestException e) {
            return "badRequest";
        } catch (NotFoundException e) {
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
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        return "addApartment";
    }


    @PostMapping("/apartmentResult")
    public String showAddCustomerForm(@ModelAttribute("apartment") Apartment apartment,
                                      @RequestParam("title") String title,
                                      @RequestParam("address") String address,
                                      @RequestParam("guestCapacity") int guestCapacity,
                                      @RequestParam("rating") int rating,
                                      @RequestParam("rooms") int rooms, Model model) {
        try {
            model.addAttribute("apartment", apartmentService.addApartment(apartment));
            return "apartmentResult";
        } catch (BadRequestException e) {
            return "badRequest";
        }
    }


    @GetMapping("/deleteApartmentForm")
    public String showDeleteApartmentForm(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("activeLink", "Apartment");
        return "deleteApartmentForm";
    }

    @GetMapping("/deleteApartmentResult")
    public String showDeleteApartmentForm(@ModelAttribute("apartment") Apartment apartment,
                                          @RequestParam("id") String id, Model model) {
        try {
            apartmentService.deleteApartmentById(id);
            model.addAttribute("activeLink", "Apartment");
            return "deleteApartmentResult";
        } catch (NotFoundException e) {
            return "notFound";
        }
    }

    @GetMapping("/updateApartmentForm")
    public String showUpdateForm(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("activeLink", "Apartment");
        return "updateApartmentForm";
    }

    @GetMapping("/updateApartmentResult")
    public String showUpdateCustomerForm(@ModelAttribute("apartment") Apartment apartmentToUpdate,
                                         @RequestParam("id") String id, Model model) {

        try {
            model.addAttribute("apartment", apartmentService.updateApartmentById(id, apartmentToUpdate));
            model.addAttribute("activeLink", "Apartment");
            return "updateApartmentResult";
        } catch (BadRequestException e) {
            return "badRequest";
        } catch (NotFoundException e) {
            return "notFound";
        }
    }

}



