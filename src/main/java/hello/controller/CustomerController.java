package hello.controller;
import hello.model.Customer;
import hello.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository respository;

    @PostMapping(path= "/{addCustomer}")
    public String addCustomer(@RequestBody Customer customer) {
        Customer saved = respository.save(customer);
        return "Added customer with id: " +  saved.getId();
    }

    @GetMapping(path= "/")
    public List<Customer> getAllCustomers() {
        return respository.findAll();
    }

    @GetMapping(path="/{getCustomerById")
    public Optional<Customer> selectCustomerById(@PathVariable String id) {
        return respository.findById(id);
  }
  @DeleteMapping(path = "/{delete}")
  public String  deleteCustomerById(String id) {
      respository.deleteById(id);
      return "Customer deleted with"  + id;

  }
  @PutMapping(path = "/{id}")
  public String updateCustomerById(String id, Customer customerToUpdate) {
   Customer saved =  respository.save(customerToUpdate);
    return "customer is now "  + saved.getName();

}
}
