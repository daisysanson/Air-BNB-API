package hello.controller;

import hello.model.Apartment;
import hello.model.Customer;
import hello.service.ApartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.stream.Location;
import java.util.List;

@RequestMapping("/api/v1/customers")
@RestController
public class ApartmentController {
    private ApartmentService apartmentService;
    static Logger log = Logger.getLogger(hello.controller.ApartmentController.class);


    @Autowired
    public ApartmentService(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PostMapping
    public ResponseEntity<Apartment> addLocation(@RequestBody Apartment apartment) {
        Location location = apartmentService.addLocation(apartment);
        log.info("location added");
        return ResponseEntity.status(HttpStatus.OK).body(apartment);

    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        log.info("customers found");
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }





}