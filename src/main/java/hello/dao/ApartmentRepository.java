package hello.dao;


import hello.model.Apartment2;
import hello.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApartmentRepository extends MongoRepository<Apartment2,String> {

}