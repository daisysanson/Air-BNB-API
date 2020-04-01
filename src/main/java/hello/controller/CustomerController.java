package hello.controller;
import hello.model.Customer;
import hello.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository respository;

    @PostMapping
    public ResponseEntity addCustomer(Customer customer) {
        Customer saved = respository.save(customer);
        return ResponseEntity.ok(saved);
    }

    @RequestMapping(path= "/{getAll}", method = RequestMethod.GET)
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.ok(respository.findAll());
    }

    @GetMapping
    public ResponseEntity selectCustomerById(String id) {
        return ResponseEntity.ok(respository.findById(id));
  }
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteCustomerById(String id) {
      respository.deleteById(id);
      return ResponseEntity.status(HttpStatus.OK).body("NO");

  }
  @PutMapping(path = "/{id}")
  public ResponseEntity updateCustomerById(String id, Customer customerToUpdate) {
      return ResponseEntity.ok(respository.save(customerToUpdate));

}
}
