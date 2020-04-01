package hello.controller;

import hello.model.Customer;
import hello.model.CustomerModel;
import hello.repository.MongoDBRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerMController {

    @Autowired
    private MongoDBRespository respository;

    @PostMapping
    public ResponseEntity addCustomer(CustomerModel customer) {
        CustomerModel saved = respository.save(customer);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAllCustomers() {
        return ResponseEntity.ok(respository.findAll());
    }
//
//    public ResponseEntity selectCustomerById(UUID id) {
//        return super.selectCustomerById(id);
//    }
//
//    public ResponseEntity deleteCustomerById(UUID id) {
//        return super.deleteCustomerById(id);
//    }
//
//    public ResponseEntity updateCustomerById(UUID id, @Valid @NotNull Customer customerToUpdate) {
//        return super.updateCustomerById(id, customerToUpdate);
//    }
}
