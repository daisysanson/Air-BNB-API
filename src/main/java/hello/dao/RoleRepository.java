package hello.dao;

import hello.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RoleRepository extends MongoRepository<Role, String> {

    @Query("{ 'role' : ?0 }")
    Role findByRole(String role);
}