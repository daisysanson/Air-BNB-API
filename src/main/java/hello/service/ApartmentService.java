package hello.service;

import hello.dao.ApartmentRepository;
import hello.dao.CustomerRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.MultiErrorException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment;
import hello.model.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@Service
public class ApartmentService {

    private ApartmentRepository apartmentRepository;


    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public Apartment selectApartmentById(String id) {
        Optional<Apartment> searchApartments = apartmentRepository.findById(id);
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");

        }
        if (!apartmentRepository.existsById(id)) {
            throw new NotFoundException("Cannot find this ID");
        }
//        status(HttpStatus.OK).body(searchCustomer.get());
        return apartmentRepository.findById(id).get();
    }


    public List<Apartment> getAllApartments() {

        return apartmentRepository.findAll();
    }


    public Apartment addApartment(Apartment apartment) {
       // List<String> errors = new ArrayList<>(); //make new list which will contrain of errors
            apartmentRepository.insert(apartment);
            return apartment;
        }


    public boolean deleteApartmentById(String id) {
        {
            if (!apartmentRepository.existsById(id)) {
                throw new NotFoundException("id " + id + "  not found");
            }
            apartmentRepository.deleteById(id);
            return true;

        }
    }

    public Apartment updateApartmentById(@PathVariable String id, Apartment apartmentToUpdate) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isBlank(apartmentToUpdate.getTitle())) {
            throw new BadRequestException("Please enter a name");
        }
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");
        }
        if (!apartmentRepository.existsById(id)) {
            throw new NotFoundException("id " + id + " not found");

        }
        if (!errors.isEmpty()) {
            throw new MultiErrorException("The apartment data is incorrect", errors);
        } else

            return apartmentRepository.save(apartmentToUpdate);
    }
}


