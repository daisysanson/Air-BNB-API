package hello.dao;
import hello.model.Customer;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("hello/dao")
public class CustomerDataAccessService implements CustomerDao {
    private static List<Customer> db = new ArrayList<>();


    public Customer insertCustomer(UUID id, Customer customer) {
        Customer updatedCustomer = new Customer(id, customer.getName(), customer.isBookingConfirmed());
        db.add(updatedCustomer);
        return updatedCustomer; //return back the data
    }

    public Customer insertCustomer(Customer customer) {
        UUID id = UUID.randomUUID();
        return insertCustomer(id, customer);
    }

    public List<Customer> selectAllCustomers() {
        return db;
    }

    @Override
    public Optional<Customer> selectCustomerById(UUID id) { //search database for person with given id
        return db.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteCustomerById(UUID id) {
        Optional<Customer> findCustomer = selectCustomerById(id);
        if (!findCustomer.isPresent()) {
            return 1;
        } else {
            db.remove(findCustomer.get());
            return 0;
        }
    }


    @Override
    public int updateCustomerByiD(UUID id, Customer update) { //get the customer update object from client, this 'update' doesn't contain an id
        return selectCustomerById(id) //select existing record in database
                .map(customer1 -> { //maps the client request to the customer that we've found
                    int indexOfCustomerToUpdate = db.indexOf(customer1); //return index
                    if (indexOfCustomerToUpdate >= 0) { //if bigger or = 0  create a new customer from data collected in'update' parameter
                        db.set(indexOfCustomerToUpdate, new Customer(id, update.getName(), update.isBookingConfirmed())); //insert the customer that we've received from client to position where other customer was in database
                        return 1;
                    } return 0;

                }).orElse(0);
}
}
