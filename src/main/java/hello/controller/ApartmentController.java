package hello.controller;


import hello.exceptions.BadRequestException;
import hello.model.Apartment;
import hello.service.ApartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/apartments")
@RestController
public class ApartmentController {
    private static Logger log = Logger.getLogger(hello.controller.ApartmentController.class);

    private ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }


    @PostMapping
    public ResponseEntity<Apartment> addApartment(@RequestBody Apartment apartment) {
        try{
        Apartment apartment1 = apartmentService.addApartment(apartment);
        log.info("apartment added");
        return ResponseEntity.status(HttpStatus.OK).body(apartment);}
        catch (BadRequestException e) {
            throw new BadRequestException("Invalid data");

        }

    }

    @GetMapping
    public ResponseEntity<List<Apartment>> getAllApartments() {
        List<Apartment> apartments = apartmentService.getAllApartments();
        log.info("apartments found");
        return ResponseEntity.status(HttpStatus.OK).body(apartments);
    }

    @GetMapping(path = "/{id}") //id will appear in the path....i.e //someId
    public ResponseEntity<Apartment> selectApartmentById(@PathVariable("id") String id) {
        Apartment apartment = apartmentService.selectApartmentById(id);
        if(apartment ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("apartment found");
        return ResponseEntity.status(HttpStatus.OK).body(apartment);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteApartmentById(@PathVariable("id") String id) {
        apartmentService.deleteApartmentById(id);
        log.info("apartment deleted");
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateApartmentById(@PathVariable("id") String id, @Valid @RequestBody Apartment apartmentToUpdate) {
        log.info("apartment updated");
        Apartment apartment1 = apartmentService.updateApartmentById(id, apartmentToUpdate);

        return ResponseEntity.status(HttpStatus.OK).body("apartment at id " + id + " has been replaced with " + apartmentToUpdate.getTitle());

    }
}
