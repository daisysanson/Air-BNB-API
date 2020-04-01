package hello.dao;


import hello.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface CustomerRepository extends MongoRepository<Customer, String> {

//    public ResponseEntity addCustomer(Customer customer);
//
//    public ResponseEntity getAllCustomers() ;
//
//    public ResponseEntity selectCustomerById(String id);
//
//    public ResponseEntity<String> deleteCustomerById(String id);
//
//    public ResponseEntity updateCustomerById(String id, Customer customerToUpdate);


}
