package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;

@Document(collection = "apartments")
@RestController
public class Apartment {

    @Id
    private String id;

    @Field(value = "title")
    @NotNull
    private String title;

    @Field(value = "location")
    private String location;

    @Field(value = "checkInDate")
    @NotNull
    private Date checkInDate;

    @Field(value = "checkOutDate")
    @NotNull
    private Date checkOutDate;

    @Field(value = "guests")
    private HashMap<Integer, String> guests = new HashMap<Integer, String>();


    public Apartment(String id,
                     @NotNull String title,
                     String location,
                     HashMap<Integer, String> guests) {

        this.id = id;
        this.title = title;
        this.location = location;
        this.guests = guests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public HashMap<Integer, String> getGuests() {
        return guests;
    }

    public void setGuests(HashMap<Integer, String> guests) {
        this.guests = guests;
    }
}
