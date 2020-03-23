package hello.dao;


import hello.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("hello/dao")
public class CustomerDataAccessService implements CustomerDao{
    private static List<Customer> db = new ArrayList<>();


    public int insertCustomer(UUID id, Customer customer) {
        db.add(new Customer(id,customer.getName(), customer.isBookingConfirmed()));
        return 1; //return 1 so we know insertion always works
    }

    public int insertCustomer(Customer customer) {
        UUID id = UUID.randomUUID();
        return insertCustomer(id,customer);
    }

public List<Customer> selectAllCustomers(){
        return db;
}
}
