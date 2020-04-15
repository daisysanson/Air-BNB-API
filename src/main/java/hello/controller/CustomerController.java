package hello.controller;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sun.istack.internal.NotNull;
import hello.exceptions.*;
import hello.model.Customer;
import hello.dao.CustomerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.net.BindException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository respository;

    static Logger log = Logger.getLogger(CustomerController.class);


    @PostMapping
    public ResponseEntity addCustomer(@RequestBody Customer customer){ //to turn json object in java customer
        System.out.println(customer);
        if ((customer.getName().isEmpty()) || (customer.getName() == null)) {
            log.debug("Hello World");

            throw new BadRequestException("Customer name needs to be entered");

    }if (customer.isBookingConfirmed() == null) {
            throw new BadRequestException("booking request cannot be empty");
        } else {

        }
            return status(HttpStatus.OK).body(respository.insert(customer));
        }


    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return status(HttpStatus.OK).body(respository.findAll());
    }


    @GetMapping(path = "/{id}") //id will appear in the path....i.e //someId
    public ResponseEntity selectCustomerById(@PathVariable("id") String id) { //grab id and turn it into a UUID
        Optional<Customer> searchCustomer = respository.findById(id);
        if (!searchCustomer.isPresent()) {
            throw new NotFoundException("Cannot find this ID");
        }
        return status(HttpStatus.OK).body(searchCustomer.get());
    }



    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCustomerById(@PathVariable("id") String id) {
        Optional<Customer> findCustomer = respository.findById(id);
        if (!findCustomer.isPresent()) {
            throw new NotFoundException("id " + id + "  not found");
        }

        respository.deleteById(id);
        return status(HttpStatus.OK).body("id " + id + " has been deleted");
    }



   @PutMapping(path = "/{id}")
  public ResponseEntity updateCustomerById(@PathVariable("id") String id, @Valid @NotNull @RequestBody Customer customerToUpdate) {
      Optional<Customer> searchCustomer = respository.findById(id);
       if (respository.findById(id).isPresent()) {
           return status(HttpStatus.OK).body("'name' " + searchCustomer.get().getName() +
                   " at id " + id + " has been replaced by " + customerToUpdate.getName());
      } else {
           throw new NotFoundException(id + " not found");
    }
  }
}
