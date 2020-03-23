package hello.service;


import hello.dao.CustomerDao;
import hello.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //can use 'componenet' but clearer to annotate what it is
public class CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(@Qualifier("hello/dao") CustomerDao customerDao) { //qaulifiers say which implementaion you are using
        this.customerDao = customerDao;
    }

    public int insertCustomer(Customer customer){
        return customerDao.insertCustomer(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectAllCustomers();
    }
}
