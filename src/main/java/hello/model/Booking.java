package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;

    @DBRef
    private Customer customer;

    @DBRef
    private Apartment apartment;

    public Booking(Customer customer, Apartment apartment) {
        this.customer = customer;
        this.apartment = apartment;
    }

    public Booking() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


