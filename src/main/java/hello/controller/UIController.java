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
    private CustomerService customerService;

    @Autowired
    public UIController(CustomerService customerService) {
        this.customerService = customerService;
    }

    static Logger log = Logger.getLogger(CustomerController.class);

    @GetMapping("/index")
    public String getCustomerId(Model model) {
        return "index";
    }

    @GetMapping("/customer")
    public String showCustomerLandingPage(Model model){
    model.addAttribute("activeLink", "Customer");
        return "customer";
    }

    @GetMapping("/findACustomerForm")
    public String showGetCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        model.addAttribute("activeLink", "Customer");
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
            model.addAttribute("activeLink", "Customer");
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
        model.addAttribute("activeLink", "Customer");
        return "getAllCustomers";
    }

    @GetMapping("/addCustomerForm")
    public String showAddForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("activeLink", "Customer");
        return "addCustomerForm";
    }


    @PostMapping("/addResult")
    public String showAddCustomer(@ModelAttribute("customer") Customer customer,
                              @RequestParam("name") String name,
                              @RequestParam("bookingConfirmed") Boolean bookingConfirmed, Model model) {
        try {
            model.addAttribute("customer", customerService.addCustomer(customer));
            model.addAttribute("activeLink", "Customer");
            return "addResult";
        } catch (MultiErrorException e) {
            log.info("name field is empty");
            return "badRequest";
        }
    }


    @GetMapping("/deleteCustomerForm")
    public String showDeleteForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("activeLink", "Customer");
        return "deleteCustomerForm";
    }

    @GetMapping("/customerDeleted")
    public String showDeleteCustomer(@ModelAttribute("customer") Customer customer,
                                 @RequestParam("id") String id, Model model) {
        try {
            model.addAttribute("customer", customerService.deleteCustomerById(id));
            model.addAttribute("activeLink", "Customer");
            return "customerDeleted";
        } catch (NotFoundException e) {
            log.info("customer with id" + id + "not found");
            return "notFound";
        }
    }


    @GetMapping("/updateCustomerForm")
    public String showUpdateForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("activeLink", "Customer");
        return "updateCustomerForm";
    }

    @GetMapping("/updateCustomer")
    public String showUpdateCustomer(@ModelAttribute("customer") Customer customerToupdate,
                                 @RequestParam("id") String id, Model model) {

        try {
            model.addAttribute("customer", customerService.updateCustomerById(id, customerToupdate));
            model.addAttribute("activeLink", "Customer");
            return "updateCustomer";
        } catch (BadRequestException e) {
            log.info("customer with id " + id + "not found");
            return "badRequest";
        } catch (NotFoundException e) {
            return "notFound";
        }
    }

}
