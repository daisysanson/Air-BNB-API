package hello.controller;
import com.sun.istack.internal.NotNull;
import hello.exceptions.*;
import hello.model.Customer;
import hello.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository respository;


    @PostMapping
    public ResponseEntity addCustomer(@RequestBody @Valid Customer customer) { //to turn json object in java customer
        if ((customer.getName().isEmpty()) || (customer.getName() == null)) {
            throw new BadRequestException("Please enter a valid 'name'");

        }
        if (customer.isBookingConfirmed() == null) {
            throw new BadRequestException("Please define the booking status as either 'true' or false'");

        }
       else if (customer.isBookingConfirmed() == null
                && customer.getName() == null) {
            throw new BadRequestException("Please complete all the fields");

        } else {
            return status(HttpStatus.OK).body(respository.insert(customer));
        }
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
        respository.deleteById(id);
        if (respository.findById(id).isPresent()) {
        return status(HttpStatus.OK).body("id " + id + " has been deleted");
        } else {
         throw new NotFoundException("id " + id + "  not found");
        }
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
