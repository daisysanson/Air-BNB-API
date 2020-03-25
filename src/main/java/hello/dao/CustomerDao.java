package hello.dao;
import hello.model.Customer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDao {

    Customer insertCustomer(UUID id, Customer customer);//insert a person, mocks database by using a list

    Customer insertCustomer(Customer customer);

    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(UUID id);

    int deleteCustomerById(UUID id);

    int updateCustomerByiD(UUID id, Customer customer);




}


