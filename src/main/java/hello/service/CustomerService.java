package hello.service;
import hello.dao.CustomerDao;
import hello.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(@Qualifier("hello/dao") CustomerDao customerDao) { //qaulifiers say which implementaion you are using
        this.customerDao = customerDao;
    }

    public Customer insertCustomer(Customer customer) {
        return customerDao.insertCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Optional<Customer> getCustomerById(UUID id) {
        return customerDao.selectCustomerById(id);
    }

    public int deleteCustomer(UUID id){
        return customerDao.deleteCustomerById(id);}

        public int updateCustomerById(UUID id, Customer newCustomer){
        return customerDao.updateCustomerByiD(id, newCustomer);
        }

}