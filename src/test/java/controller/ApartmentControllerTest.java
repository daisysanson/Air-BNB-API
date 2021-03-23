package controller;


import hello.controller.ApartmentController;
import hello.model.Apartment;
import hello.service.ApartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApartmentControllerTest {
    private static final String APARTMENT_ID = "012345";
    private static final String TITLE = "";
    private static final String ADDRESS = "012345";
    private static final int GUEST_CAPACITY = 2;
    private static final int ROOMS = -2;
    private static final int RATING = -3;

    @Mock
    private ApartmentService apartmentService;


    @InjectMocks
    private ApartmentController controller;

    @Test
    @DisplayName("Apartment found by id")
    public void testFindApartment() {
        Apartment apartment = new Apartment();

        when(apartmentService.selectApartmentById(APARTMENT_ID)).thenReturn(apartment);
        ResponseEntity<Apartment> response = controller.selectApartmentById(APARTMENT_ID);

        assertEquals(apartment, response.getBody());
    }

    @Test
    @DisplayName("No Apartment found by id")
    void testNoApartmentFoundById() {
        Apartment apartment = new Apartment();


        when(apartmentService.selectApartmentById(apartment.getId())).thenReturn(null);
        ResponseEntity<Apartment> response = controller.selectApartmentById(apartment.getId());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    @DisplayName("Adding apartment ")
    void testApartmentAdd(){
        Apartment apartment = new Apartment();
        apartment.setTitle(TITLE);
        apartment.setAddress(ADDRESS);
        apartment.setGuestCapacity(GUEST_CAPACITY);
        apartment.setRooms(ROOMS);
        apartment.setRating(RATING);

        ResponseEntity<Apartment> response = controller.addApartment(apartment);
        assertNotNull(apartment);


        assertEquals(apartment,response.getBody());

    }

    @Test
    @DisplayName("Delete apartment ")
    void testApartmentDelete(){
        Apartment apartment = new Apartment();
        apartment.setId(APARTMENT_ID);
        apartment.setTitle(TITLE);
        apartment.setAddress(ADDRESS);
        apartment.setGuestCapacity(GUEST_CAPACITY);
        apartment.setRooms(ROOMS);
        apartment.setRating(RATING);

        ResponseEntity response = controller.deleteApartmentById(apartment.getId());



        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


}



