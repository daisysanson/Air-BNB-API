package hello.dao;


import hello.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {

     @Query("{ 'name' : ?0 }")
     List<Customer> findByName(String name);


}
