package hello.controller;

import hello.exceptions.BadRequestException;
import hello.exceptions.ForbiddenException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment;
import hello.model.HostBooking;
import hello.model.User;
import hello.model.UserUtil;
import hello.service.ApartmentService;
import hello.service.HostBookingService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.Forbidden;


@Controller
public class ApartmentUIController {
    private ApartmentService apartmentService;
    private UserService userService;
    private HostBookingService hostBookingService;


    @Autowired
    public ApartmentUIController(ApartmentService apartmentService, UserService userService, HostBookingService hostBookingService) {
        this.apartmentService = apartmentService;
        this.userService = userService;
        this.hostBookingService = hostBookingService;
    }


    @GetMapping("/apartment")
    public String getApartmentLandingPage(Model model) {
        model.addAttribute("activeLink", "Apartment");
        model.addAttribute("title", "Apartments");
        return "apartment";
    }

    @GetMapping("/findAnApartment")
    public String showFindApartmentForm(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("activeLink", "Apartment");
        model.addAttribute("title", "Find an Apartment");
        return "findAnApartment";
    }

    @PostMapping("/findApartmentResult")
    public String showFindApartmentResult(@ModelAttribute("apartment") Apartment apartment, @RequestParam("title") String title, Model model) {
        try {

            model.addAttribute("activeLink", "Apartment");
            model.addAttribute("title", "Result!");

            model.addAttribute("apartment", apartmentService.selectApartmentByTitle(apartment.getTitle()));


        } catch (BadRequestException e) {
            return "badRequest";
        } catch (NotFoundException e) {
            return "notFound";
        }catch (NullPointerException e) {
            return "notFound";
        }
        return "findApartmentResult";
    }


    @GetMapping("/showAllApartments")
    public String showAllApartments(Model model) {
        model.addAttribute("apartment", apartmentService.getAllApartments());
        model.addAttribute("activeLink", "Apartment");
        model.addAttribute("title", "All Apartments");
        return "showAllApartments";
    }


    @GetMapping("/addApartment")
    public String showAddForm(Model model) {

        Apartment apartment = new Apartment();
        HostBooking hostBooking = new HostBooking();
        model.addAttribute("apartment", apartment);
        model.addAttribute("host", hostBooking);
        model.addAttribute("title", "Add a New Apartment");
        return "addApartment";
    }


    @PostMapping("/apartmentResult")
    public String showAddCustomerForm(@ModelAttribute("apartment") Apartment apartment,
                                      @ModelAttribute("host") HostBooking hostBooking,
                                      @RequestParam("title") String title,
                                      @RequestParam("address") String address,
                                      @RequestParam("guestCapacity") int guestCapacity,
                                      @RequestParam("rating") int rating,
                                      @RequestParam("rooms") int rooms, Model model) {
        try {
            model.addAttribute("apartment", apartmentService.addApartment(apartment));
            User user = userService.findUserByEmail(UserUtil.userName());
            hostBooking.setUser(user);
            hostBooking.setApartment(apartment);
            model.addAttribute("host", hostBookingService.addHostBooking(hostBooking));
            model.addAttribute("title", "Success");
            return "apartmentResult";
        } catch (BadRequestException e) {
            return "badRequest";
        }
    }


    @GetMapping("/deleteApartmentForm/{id}")
    public String showDeleteApartmentForm(Model model, @PathVariable("id") String id) {
        model.addAttribute("booking", hostBookingService.selectHostBookingById(id));
        model.addAttribute("activeLink", "Apartment");
        model.addAttribute("title", "Delete an Apartment");
        return "deleteApartmentForm";
    }

    @GetMapping("/deleteApartmentResult")
    public String showDeleteApartmentForm(@ModelAttribute("booking") HostBooking booking,
                                          @RequestParam("id") String id, Model model) {
      try {
          HostBooking booking1 = hostBookingService.selectHostBookingById(id);
            apartmentService.deleteApartmentById(booking1.getApartment().getId());
            hostBookingService.deleteHostBookingById(id);
            model.addAttribute("activeLink", "Apartment");
            model.addAttribute("title", "Success!");
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
        model.addAttribute("title", "Update an Apartment");
        return "updateApartmentForm";
    }



    @GetMapping(value= "/updateSpecificApartment/{id}")
    public String showSpecUpdateForm(Model model, @PathVariable("id") String apartmentId) {
        model.addAttribute("apartment", apartmentService.selectApartmentById(apartmentId));
        model.addAttribute("activeLink", "Apartment");
        model.addAttribute("title", "Update an Apartment");
        return "updateSpecificApartment";
    }




    @GetMapping("/updateApartmentResult")
    public String showUpdateCustomerForm(@ModelAttribute("apartment") Apartment apartmentToUpdate,
                                         @RequestParam("id") String id, Model model) {

        try {
            model.addAttribute("apartment", apartmentService.updateApartmentById(id, apartmentToUpdate));
            model.addAttribute("activeLink", "Apartment");
            model.addAttribute("title", "Success!");
            return "updateApartmentResult";
        } catch (ForbiddenException e) {
            return "forbidden";
        } catch (BadRequestException e) {
            return "badRequest";
        } catch (NotFoundException e) {
            return "notFound";
        }
    }






}



