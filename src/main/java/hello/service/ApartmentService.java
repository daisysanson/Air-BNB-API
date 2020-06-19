package hello.service;

import hello.dao.ApartmentRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment2;
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

    public Apartment2 selectApartmentById(String id) {
        Optional<Apartment2> searchApartments = apartmentRepository.findById(id);
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");

        }
        if (!apartmentRepository.existsById(id)) {
            throw new NotFoundException("Cannot find this ID");
        }
        status(HttpStatus.OK).body(searchApartments.get());
        return apartmentRepository.findById(id).get();
    }


    public List<Apartment2> getAllApartments() {

        return apartmentRepository.findAll();
    }



    public Apartment2 addApartment(Apartment2 apartment) {
        if ((StringUtils.isBlank(apartment.getTitle())) || ((StringUtils.isBlank(apartment.getAddress())))) {
            throw new BadRequestException("Please enter the apartment title");
        }
        if ((apartment.getGuestCapacity() <= 0) || ((apartment.getGuestCapacity() > 14))) {
            throw new BadRequestException("Guest Capacity can not be less than 0 or more than 14");
        } else

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


    public Apartment2 updateApartmentById(@PathVariable String id, Apartment2 apartmentToUpdate) {

        if (StringUtils.isBlank(apartmentToUpdate.getTitle()) || ((StringUtils.isBlank(apartmentToUpdate.getAddress())))) {
            throw new BadRequestException("Please ensure all fields are completed");

        }
//        if (apartmentToUpdate.getOccupiedEndDate().before(apartmentToUpdate.getOccupiedStartDate())
//                || apartmentToUpdate.getOccupiedStartDate().after(apartmentToUpdate.getOccupiedEndDate())) {
//            throw new BadRequestException("Please ensure dates are chronological i.e start date is before end date");

//        }
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");
        }
        if (!apartmentRepository.existsById(id)) {
            throw new NotFoundException("id " + id + " not found");

        }
        if ((apartmentToUpdate.getGuestCapacity() <= 0) || ((apartmentToUpdate.getGuestCapacity() > 14)) ) {
            throw new BadRequestException("Guest Capacity can not be less than 0 or more than 14");
        } else

            return apartmentRepository.save(apartmentToUpdate);
    }
}


