package hello.service;

import hello.dao.BookingRepository;
import hello.dao.RoleRepository;
import hello.model.Booking;
import hello.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role selectRoleById(String id) {
        return repository.findById(id).get();
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    public Role getRole(String role){
        return repository.findByRole(role);
    }

}
