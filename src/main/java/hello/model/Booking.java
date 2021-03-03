package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Apartment apartment;

    private Date bookingDate;

    public Booking(User user, Apartment apartment) {
        this.user = user;
        this.apartment = apartment;
    }

    public Booking() {
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}


