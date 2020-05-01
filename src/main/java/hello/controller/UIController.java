package hello.controller;

import hello.dao.CustomerRepository;
import hello.model.Customer;
import hello.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequestMapping("/")
@Controller
public class UIController {
    private Customer customer;
    private CustomerRepository repository;
    private CustomerService customerService;

    @Autowired
    public UIController(Customer customer, CustomerRepository repository, CustomerService customerService) {
        this.customer = customer;
        this.repository = repository;
        this.customerService = customerService;
    }




    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getCustomerId(Model model) {
        return "/index";
    }

    @RequestMapping(value = "/findACustomerForm", method = RequestMethod.GET)
    public String showGetCustomerForm(Model model) {
        customer.getId();

        model.addAttribute("customer", customer);
        return "/findACustomerForm";
    }

    @RequestMapping(value = "/showCustomer", method = RequestMethod.POST)
    public String showGetCustomerPage(@ModelAttribute("customer") Customer customer,
                           @RequestParam("id") String id, Model model) {
        model.addAttribute("customer", customerService.selectCustomerById(id));
        return "/showCustomer";

    }


    @RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public String showAllCustomers(Model model) {
        model.addAttribute("customer", customerService.getAllCustomers());
        return "/getAllCustomers";
    }

    @RequestMapping(value = "/addCustomerForm", method = RequestMethod.GET)
    public String showAddForm(Model model) {

        model.addAttribute("customer", customer);
        return "/addCustomerForm";
    }


    @RequestMapping(value = "/addResult", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") Customer customer,
                              @RequestParam("name") String name,
                              @RequestParam("bookingConfirmed") Boolean bookingConfirmed, Model model) {
        model.addAttribute("customer", customerService.addCustomer(customer));
        return "/addResult";

    }

    @RequestMapping(value = "/deleteCustomerForm", method = RequestMethod.GET)
    public String showDeleteForm(Model model) {
        model.addAttribute("customer", customer);
        return "/deleteCustomerForm";
    }

    @RequestMapping(value = "/customerDeleted", method = RequestMethod.GET)
    public String deleteCustomer(@ModelAttribute("customer") Customer customer,
                                 @RequestParam("id") String id, Model model) {
        model.addAttribute("customer", customerService.deleteCustomerById(id));
        return "/customerDeleted";

    }


    @RequestMapping(value = "/replaceCustomerForm", method = RequestMethod.GET)
    public String showupdateForm(Model model) {
        model.addAttribute("customer", customer);
        return "/replaceCustomerForm";
    }


    @RequestMapping(value = "/replaceCustomer", method = RequestMethod.GET)
    public String updateCustomer(@ModelAttribute("customer") Customer customerToupdate,
                                 @RequestParam("id") String id, Model model) {

        model.addAttribute("customer", customerService.updateCustomerById(id, customerToupdate));
        return "/replaceCustomer";

    }
}
