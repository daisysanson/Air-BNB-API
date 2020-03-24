package hello.dao;


import hello.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDao {

    Customer insertCustomer(UUID id, Customer customer);//insret a person, mocks database by using a list

    Customer insertCustomer(Customer customer);

    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(UUID id); //not sure whether value will be present s

    String deleteCustomerById(UUID id);

    int updateCustomerById(UUID id, Customer customer); //new customer




}


