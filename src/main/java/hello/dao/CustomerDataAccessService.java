package hello.dao;


import hello.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("hello/dao")
public class CustomerDataAccessService implements CustomerDao{
    private static List<Customer> db = new ArrayList<>();


    public Customer insertCustomer(UUID id, Customer customer) {
        Customer updatedCustomer = new Customer(id,customer.getName(), customer.isBookingConfirmed());
        db.add(updatedCustomer);
        return updatedCustomer; //return back the data
    }

    public Customer insertCustomer(Customer customer) {
        UUID id = UUID.randomUUID();
        return insertCustomer(id,customer);
    }

public List<Customer> selectAllCustomers(){
        return db;
}
}
