package hello.dao;


import hello.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerDao {

    int insertCustomer(UUID id, Customer customer);//insret a person, mocks database by using a list

    int insertCustomer(Customer customer);

    List<Customer> selectAllCustomers();




}


