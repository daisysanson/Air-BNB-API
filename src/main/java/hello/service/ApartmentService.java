package hello.service;

import hello.dao.ApartmentRepository;
import hello.exceptions.BadRequestException;
import hello.exceptions.NotFoundException;
import hello.model.Apartment;
import hello.model.HostBooking;
import hello.model.User;
import hello.model.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private UserService userService;
    private HostBookingService hostBookingService;


    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, UserService userService, HostBookingService hostBookingService) {
        this.apartmentRepository = apartmentRepository;
        this.userService = userService;
        this.hostBookingService = hostBookingService;
    }

    static Logger log = Logger.getLogger(ApartmentService.class);


    public ApartmentService() {

    }



    public List<Apartment> selectApartmentByTitle(String title) {

        List<Apartment> apartments = apartmentRepository.findAll();
        List<Apartment> matchingApartment = new ArrayList<>();
        for (Apartment apartment : apartments) {
            if (apartment.getTitle().equals(title)) {
                matchingApartment.add(apartment);
            }
            if (StringUtils.isBlank(apartment.getId())) {
                log.info("No id entered");
                throw new BadRequestException("Please enter an id");
            }
            if (!apartmentRepository.existsById(apartment.getId())) {
                log.info("id not found");
                throw new NotFoundException("Cannot find this apartment");
            }

//            Optional<Apartment> searchApartments = apartmentRepository.findById(apartment.getId());
//            Optional <Apartment> searchApartments = apartmentRepository.findOne(Example.of(apartmentRepository.findByTitle(apartment.getId())));
//            status(HttpStatus.OK).body(searchApartments.get());


//            return searchApartments
        }
        return matchingApartment;
    }



    public Apartment selectApartmentById(String id) {
        if (StringUtils.isBlank(id)) {
            log.info("No id entered");
            throw new BadRequestException("Please enter an id");
        }
        if (!apartmentRepository.existsById(id)) {
            log.info("id not found");
            throw new NotFoundException("Cannot find this ID");
        } else {
            Optional<Apartment> searchApartments = apartmentRepository.findById(id);
            status(HttpStatus.OK).body(searchApartments.get());

            return apartmentRepository.findById(id).get();
        }
    }

    public List<Apartment> getAllApartments() {

        return apartmentRepository.findAll();
    }


    public Apartment addApartment(Apartment apartment) {
        if ((StringUtils.isBlank(apartment.getTitle())) || ((StringUtils.isBlank(apartment.getAddress())))) {
            log.info("apartment id not entered");
            throw new BadRequestException("Please enter the apartment title");

        }
        if ((apartment.getGuestCapacity() <= 0) || ((apartment.getGuestCapacity() > 14))) {
            log.info("Guest Capacity is less than 0 or more than 14");
            throw new BadRequestException("Guest Capacity can not be less than 0 or more than 14");

        }
        if ((apartment.getRating() < 0 || (apartment.getRating() >= 6))) {
            log.info("Rating is less than 0 or is/more than 6");
            throw new BadRequestException("Rating is out of range 0 -5");
        }
        if ((apartment.getRooms() <= 0 || (apartment.getRooms() >= 20))) {
            log.info("Room is less than 0 or is/more than 20");
            throw new BadRequestException("Room number is out of range 0 -5");

        }
        return apartmentRepository.insert(apartment);
    }


    public void deleteApartmentById(String id) {
        {
            if (!apartmentRepository.existsById(id)) {
                log.info("id not found");
                throw new NotFoundException("id " + id + "  not found");
            }
            apartmentRepository.deleteById(id);
        }
    }


    public boolean isUserHostOfApartment(String apartmentId) {
        User user = userService.findUserByEmail(UserUtil.userName());
//        List<HostBooking> userBookings = new ArrayList<>();
        List<HostBooking> bookings = hostBookingService.getAllHostBookingsForUser(user);
        for (HostBooking booking : bookings) {
            if (apartmentId.equals(booking.getApartment().getId()) && (user.getId().equals(booking.getUser().getId()))){
                return true;
            }
        }
        return false;
    }



    public Apartment updateApartmentById(@PathVariable String id, Apartment apartmentToUpdate) {

        if(!isUserHostOfApartment(id)){
            throw new BadRequestException("You do not own this apartment!");
        }

        if (StringUtils.isBlank(apartmentToUpdate.getTitle()) || ((StringUtils.isBlank(apartmentToUpdate.getAddress())))) {
            log.info("title or address not entered");
            throw new BadRequestException("Please ensure all fields are completed");

        }
        if (StringUtils.isBlank(id)) {
            log.info("id not found");
            throw new BadRequestException("Please enter an id");
        }
        if (!apartmentRepository.existsById(id)) {
            log.info("id not found");
            throw new NotFoundException("id " + id + " not found");

        }
        if ((apartmentToUpdate.getGuestCapacity() <= 0) || ((apartmentToUpdate.getGuestCapacity() > 14))) {
            log.info("Guest Capacity is less than 0 or more than 14");
            throw new BadRequestException("Guest Capacity can not be less than 0 or more than 14");

        }
        if ((apartmentToUpdate.getRating() < 0 || (apartmentToUpdate.getRating() >= 6))) {
            log.info("Rating is less than 0 or is/more than 6");
            throw new BadRequestException("Rating is out of range 0 -5");
        }
        if ((apartmentToUpdate.getRooms() <= 0 || (apartmentToUpdate.getRooms() >= 20))) {
            log.info("Room is less than 0 or is/more than 20");
            throw new BadRequestException("Room number is out of range 0 -5");
        } else

            return apartmentRepository.save(apartmentToUpdate);
    }
}


