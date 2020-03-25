package hello.controller;

import hello.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hello.service.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

//exposes some end points which clients use, i.e http methods
@RequestMapping ("/api/v1/customers")
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addCustomer(@RequestBody Customer customer) { //to turn json object in java customer
        if (customer.getName().isEmpty()) {
            return status(HttpStatus.BAD_REQUEST).body("'Name' field is invalid");
        }
        if (customer.isBookingConfirmed() == null) {
            return status(HttpStatus.BAD_REQUEST).body("'Booking_confirmed' field is invalid");


        }
        return status(HttpStatus.OK).body(customerService.insertCustomer(customer));
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    @GetMapping(path = "{id}") //it will appear in the path....i.e //someId
    public ResponseEntity selectCustomerById(@PathVariable("id") UUID id) { //grab id and turn it into a UUID
        Optional<Customer> searchCustomer = customerService.getCustomerById(id);
        if (!searchCustomer.isPresent()) {
            return status(HttpStatus.NOT_FOUND).body("ID not found"); //custom exception
        } else {
            return status(HttpStatus.OK).body(searchCustomer.get());
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteCustomerById(@PathVariable("id") UUID id) {
        return customerService.deleteCustomer(id);
    }
}
