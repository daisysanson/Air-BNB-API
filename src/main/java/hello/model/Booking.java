package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "bookings")
public class Booking {
    @DBRef
    private Customer customer;
    @DBRef
    private Apartment apartment;
    @NotNull
    @Id
    private String id;

    public Booking(Customer customer, Apartment apartment, String id) {
        this.customer = customer;
        this.apartment = apartment;
        this.id = id;
    }

    public Booking(){}

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


