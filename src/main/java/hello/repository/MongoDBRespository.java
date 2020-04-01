package hello.repository;

import hello.model.Customer;
import hello.model.CustomerModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface MongoDBRespository extends MongoRepository<CustomerModel, String> {


}
