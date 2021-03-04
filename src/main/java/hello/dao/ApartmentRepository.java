package hello.dao;


import hello.model.Apartment;
import hello.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ApartmentRepository extends MongoRepository<Apartment, String> {


}
