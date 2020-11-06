package hello.dao;

import hello.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'email' : ?0 }")
    User findByEmail(String email);

    @Query("{ 'email' : ?0 }")
    List<User> findByEmailList(String email);


}
