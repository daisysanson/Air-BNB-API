package hello.controller;


import hello.model.Apartment2;
import hello.model.Customer;
import hello.service.ApartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.stream.Location;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/apartments")
@RestController
public class ApartmentController {
    private ApartmentService apartmentService;
    static Logger log = Logger.getLogger(hello.controller.ApartmentController.class);


    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }


    @PostMapping
    public ResponseEntity<Apartment2> addApartment(@RequestBody Apartment2 apartment) {
        Apartment2 apartment1 = apartmentService.addApartment(apartment);
        log.info("apartment added");
        return ResponseEntity.status(HttpStatus.OK).body(apartment);

    }

    @GetMapping
    public ResponseEntity<List<Apartment2>> getAllApartments() {
        List<Apartment2> apartments = apartmentService.getAllApartments();
        log.info("apartments found");
        return ResponseEntity.status(HttpStatus.OK).body(apartments);
    }

    @GetMapping(path = "/{id}") //id will appear in the path....i.e //someId
    public ResponseEntity<Apartment2> selectApartmentById(@PathVariable("id") String id) {
        Apartment2 apartment = apartmentService.selectApartmentById(id);
        log.info("apartment found");
        return ResponseEntity.status(HttpStatus.OK).body(apartment);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteApartmentById(@PathVariable("id") String id) {
        apartmentService.deleteApartmentById(id);
        log.info("apartment deleted");
        return ResponseEntity.status(HttpStatus.OK).body("customer at id " + id + " has been deleted");

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateApartmentById(@PathVariable("id") String id, @Valid @RequestBody Apartment2 apartmentToUpdate) {
        log.info("apartment updated");
        Apartment2 apartment1 = apartmentService.updateApartmentById(id, apartmentToUpdate);

        return ResponseEntity.status(HttpStatus.OK).body("apartment at id " + id + " has been replaced with " + apartmentToUpdate.getTitle());

    }
}
