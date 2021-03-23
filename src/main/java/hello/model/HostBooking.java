package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "host_bookings")
public class HostBooking {


    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Apartment apartment;


    public HostBooking(User user, Apartment apartment) {
        this.user = user;
        this.apartment = apartment;
    }


    public HostBooking() {
    }

    public User getUser() {
        return user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}


