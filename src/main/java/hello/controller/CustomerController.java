package hello.controller;

import com.sun.istack.internal.NotNull;
import hello.model.Customer;
import hello.dao.CustomerRepository;
import hello.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {
    private CustomerRepository respository;
    private CustomerService customerService;
    static Logger log = Logger.getLogger(CustomerController.class);


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.addCustomer(customer);
        log.info("customer added");
        return ResponseEntity.status(HttpStatus.OK).body(customer);

    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        log.info("customers found");
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping(path = "/{id}") //id will appear in the path....i.e //someId
    public ResponseEntity<Customer> selectCustomerById(@PathVariable("id") String id) { //grab id and turn it into a UUID
        Customer customer = customerService.selectCustomerById(id);
        log.info("customer found");
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") String id) {
        customerService.deleteCustomerById(id);
        log.info("customer deleted");
        return ResponseEntity.status(HttpStatus.OK).body("customer at id " + id + " has been deleted");

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateCustomerById(@PathVariable("id") String id, @Valid @NotNull @RequestBody Customer customerToUpdate) {
        log.info("customer updated");
        Customer customer1 = customerService.updateCustomerById(id, customerToUpdate);

        return ResponseEntity.status(HttpStatus.OK).body("customer at id " + id + " has been replaced with " + customerToUpdate.getName());

    }
}


