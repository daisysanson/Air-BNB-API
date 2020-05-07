package hello.service;

import hello.dao.CustomerRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.MultiErrorException;
import hello.exceptions.NotFoundException;
import hello.model.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) { //qaulifiers say which implementaion you are using
        this.repository = repository;
    }

    public Customer selectCustomerById(String id) {
        Optional<Customer> searchCustomer = repository.findById(id);
        if (!repository.existsById(id)) {
            throw new NotFoundException("Cannot find this ID");
        }
        status(HttpStatus.OK).body(searchCustomer.get());
        return repository.findById(id).get();
    }


    public List<Customer> getAllCustomers() {

        return repository.findAll();
    }


    public Customer addCustomer(Customer customer) {
        List<String> errors = new ArrayList<>(); //make new list which will contrain of errors
        if (StringUtils.isBlank(customer.getName())) {
            errors.add("Customer name needs to be entered");
        }

        if (customer.getBookingConfirmed() == null) {
            errors.add("Booking request cannot be empty");

        }
        if (!errors.isEmpty()) {
            throw new MultiErrorException("Your customer data is incorrect", errors);
        } else {
            repository.insert(customer);
            return customer;
        }
    }


    public boolean deleteCustomerById(String id) {
        {
            if (!repository.existsById(id)) {
                throw new NotFoundException("id " + id + "  not found");
            }
            repository.deleteById(id);
            return true;

        }
    }

    public Customer updateCustomerById(@PathVariable String id, Customer customerToUpdate) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isBlank(customerToUpdate.getName())) {
            throw new BadRequestException("Please enter a name");
        }
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");
        }
        if (!repository.existsById(id)) {
            throw new NotFoundException("id " + id + " not found");

        }
        if (!errors.isEmpty()) {
            throw new MultiErrorException("Your customer data is incorrect", errors);
        } else

            return repository.save(customerToUpdate);
    }
}