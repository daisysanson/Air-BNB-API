package hello.dao;


import hello.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDao {

    Customer insertCustomer(UUID id, Customer customer);//insret a person, mocks database by using a list

    Customer insertCustomer(Customer customer);

    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(UUID id); //not sure whether value will be present s

    int deleteCustomerById(UUID id);

//    int updateCustomerByiD(UUID id, Customer customer);




}


