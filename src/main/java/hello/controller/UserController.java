package hello.controller;

import hello.dao.BookingRepository;
import hello.dao.UserRepository;
import hello.model.Booking;
import hello.model.Customer;
import hello.model.User;
import hello.service.BookingService;
import hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/api/v1/users")
@RestController
public class UserController {
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable("email") String email) {
        User users = userService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }




    @PostMapping(value = "/")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        User saveUser = userService.saveNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody User userUpdate) {
        User user = userService.updateUser(id, userUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("user id" + id + "has been updated");
    }
}

