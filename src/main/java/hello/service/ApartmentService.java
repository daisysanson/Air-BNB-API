package hello.service;

import hello.dao.ApartmentRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        if ((StringUtils.isBlank(apartment.getTitle())) || ((StringUtils.isBlank(apartment.getLocation())))){
            throw new BadRequestException("Please enter the apartment title");
        }
        if ((apartment.getOccupiedStartDate() == null) || (apartment.getOccupiedEndDate() == null)) {
            throw new BadRequestException("Please enter the dates in dd-mm-yyyy");

        }if ((apartment.getGuestCapacity() <= 0) || ((apartment.getGuestCapacity()  > 14))){
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

    public Apartment updateApartmentById(@PathVariable String id, Apartment apartmentToUpdate)  {
        List<String> errors = new ArrayList<>();

        if ((StringUtils.isBlank(apartmentToUpdate.getTitle())) || ((StringUtils.isBlank(apartmentToUpdate.getLocation())))){
            throw new BadRequestException("Please enter the apartment title");
        }
        if ((apartmentToUpdate.getOccupiedStartDate() == null) || (apartmentToUpdate.getOccupiedEndDate() == null)) {
            throw new BadRequestException("Please enter the dates in dd-mm-yyyy");
        } if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Please enter an id");
        }
        if (!apartmentRepository.existsById(id)) {
            throw new NotFoundException("id " + id + " not found");

        }if ((apartmentToUpdate.getGuestCapacity() <= 0) || ((apartmentToUpdate.getGuestCapacity()  > 14))){
            throw new BadRequestException("Guest Capacity can not be less than 0 or more than 14");
        } else

            return apartmentRepository.save(apartmentToUpdate);
    }
}


