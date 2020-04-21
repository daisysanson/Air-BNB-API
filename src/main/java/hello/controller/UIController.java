package hello.controller;

import hello.dao.CustomerRepository;
import hello.model.Customer;
import hello.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UIController {
  private Customer customer;
  private CustomerRepository repository;

    @Autowired
    public UIController(Customer customer, CustomerRepository repository) {
        this.customer = customer;
        this.repository = repository;
    }
   @GetMapping("/hello1")
    public String getAllCustomers (HttpServletRequest request, Model model) {
       model.addAttribute("customer", new Customer());
       return "customer";

   }
      @PostMapping("/hello1")
       public String addCustomer(@ModelAttribute Customer customer) {
           return "customer";

 }






}
