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


//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String showAllCustomers(Model model) {
//        model.addAttribute("customer", customerService.getAllCustomers());
//        return "customer_information";
//    }


//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String getCustomerId(@RequestParam(name = "id") String id, Model model) {
//        model.addAttribute("id", id);
//        return "index";
//    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showForm(Model model) {
        customer.getId();

        model.addAttribute("customer", customer);
        return "index";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String showPage(@ModelAttribute("customer") Customer customer,
                           @RequestParam("id") String id, Model model) {
        repository.findById(id);
        model.addAttribute("customer", customerService.selectCustomerById(id));
        return "/result";

    }
}

