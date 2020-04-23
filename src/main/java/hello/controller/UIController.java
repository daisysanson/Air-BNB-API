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


        @RequestMapping(value = "", method = RequestMethod.GET)
        public String Customer(Model model) {
            model.addAttribute("customer", customerService.getAllCustomers());
            return "customer_information";
        }
}
