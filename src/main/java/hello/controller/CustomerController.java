package hello.controller;

import hello.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import hello.service.CustomerService;

import java.util.List;

//exposes some end points which clients use, i.e http methods
@RequestMapping ("/api/v1/customer/")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) { //to turn json object in java customer
        customerService.insertCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
//
//    @GetMapping(path = "{id}") //it will appear in the path....i.e //someId
//    public Customer getCustomerById(@PathVariable("id") UUID id){ //grad id and turn it into a UUID
//        return customerService.getCustomerById(id)
//                .orElse(null);
//    }
//    @DeleteMapping(path = "{id}")
//    public void deleteCustomerById(@PathVariable("id") UUID id){
//        customerService.deleteCustomer(id);
//    }
//
//    @PutMapping(path = "{id}")
//    public void updateCustomer (@PathVariable("id") UUID id , @Valid @NotNull @RequestBody Customer customerToUpdate){
//        customerService.updateCustomer(id, customerToUpdate);
//    }
//}
