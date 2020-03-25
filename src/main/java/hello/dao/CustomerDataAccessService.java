package hello.dao;


import hello.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.status;

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

    @Override
    public Optional<Customer> selectCustomerById(UUID id) { //search database for person with given id
        return db.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public ResponseEntity deleteCustomerById(UUID id) {
        Optional<Customer> findCustomer = selectCustomerById(id);
            if (!findCustomer.isPresent()){
                return status(HttpStatus.NOT_FOUND).body("ID not found");
            } else {
                db.remove(findCustomer.get()); ///FIX THIS.////
                return status(HttpStatus.OK).body("Record successfully deleted") ;

        }
    }
    @Override
    public int updateCustomerById(UUID id, Customer customer) {
        return 0;
    }
}
