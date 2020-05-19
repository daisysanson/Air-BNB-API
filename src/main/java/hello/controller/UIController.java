package hello.controller;

import hello.exceptions.BadRequestException;
import hello.exceptions.MultiErrorException;
import hello.exceptions.NotFoundException;
import hello.model.Customer;
import hello.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UIController {
    private Customer customer;
    private CustomerService customerService;

    @Autowired
    public UIController(Customer customer, CustomerService customerService) {
        this.customer = customer;
        this.customerService = customerService;
    }

    static Logger log = Logger.getLogger(CustomerController.class);

    @GetMapping("/index")
    public String getCustomerId(Model model) {
        return "index";
    }

    @GetMapping("/findACustomerForm")
    public String showGetCustomerForm(Model model) {
        model.addAttribute("customer",customer);
        return "findACustomerForm";
    }

    @PostMapping("/showCustomer")
    public String showGetCustomerPage(@ModelAttribute("customer") Customer customer, @RequestParam("id") String id, Model model) {
        if (customer == null) {
            log.info("no customer input model");
            return "badRequest";
        }
        try {
            model.addAttribute("customer", customerService.selectCustomerById(id));
        } catch (BadRequestException e) {
            log.info("name field is empty");
            return "badRequest";
        } catch (NotFoundException e) {
            log.info("customer with id" + id + " not found");
            return "notFound";
        }
        return "showCustomer";
    }


    @GetMapping("/getAllCustomers")
    public String showAllCustomers(Model model) {
        model.addAttribute("customer", customerService.getAllCustomers());
        return "getAllCustomers";
    }

    @GetMapping("/addCustomerForm")
    public String showAddForm(Model model) {

        model.addAttribute("customer", customer);
        return "addCustomerForm";
    }


    @PostMapping("/addResult")
    public String addCustomer(@ModelAttribute("customer") Customer customer,
                              @RequestParam("name") String name,
                              @RequestParam("bookingConfirmed") Boolean bookingConfirmed, Model model) {
        try {
            model.addAttribute("customer", customerService.addCustomer(customer));
            return "addResult";
        } catch (MultiErrorException e) {
            log.info("name field is empty");
            return "badRequest";
        }
    }


    @GetMapping("/deleteCustomerForm")
    public String showDeleteForm(Model model) {
        model.addAttribute("customer", customer);
        return "deleteCustomerForm";
    }

    @GetMapping("/customerDeleted")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer,
                                 @RequestParam("id") String id, Model model) {
        try {
            model.addAttribute("customer", customerService.deleteCustomerById(id));
            return "customerDeleted";
        } catch (NotFoundException e) {
            log.info("customer with id" + id + "not found");
            return "notFound";
        }
    }


    @GetMapping("/replaceCustomerForm")
    public String showUpdateForm(Model model) {
        model.addAttribute("customer", customer);
        return "replaceCustomerForm";
    }

    @GetMapping("/replaceCustomer")
    public String updateCustomer(@ModelAttribute("customer") Customer customerToupdate,
                                 @RequestParam("id") String id, Model model) {

        try {
            model.addAttribute("customer", customerService.updateCustomerById(id, customerToupdate));
            return "replaceCustomer";
        } catch (BadRequestException e) {
            log.info("customer with id " + id + "not found");
            return "badRequest";
        } catch (NotFoundException e) {
            return "notFound";
        }
    }

}
