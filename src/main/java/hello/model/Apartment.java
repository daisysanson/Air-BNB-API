package hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "apartments2")
public class Apartment {
    @Id
    private String id;
    @Field(value = "title")
    private String title;
    @Field(value = "address")
    private String address;
    @Field(value = "guestCapacity")
    private int guestCapacity;
    @Field(value = "rooms")
    private int rooms;
    @Field(value = "rating")
    private int rating;
    @Field(value = "startDate")
    private Date startDate;

    @Field(value = "endDate")
    private Date endDate;

    public Apartment() {
    }

    public Apartment(@JsonProperty("id") String id,
                     @JsonProperty("title") String title,
                     @JsonProperty("address") String address,
                     @JsonProperty("guest_capacity") int guestCapacity,
                     @JsonProperty("rooms") int rooms,
                     @JsonProperty("rating") int rating,
                    @JsonProperty("start_date") Date startDate,
                    @JsonProperty("end_date") Date endDate)
    {
        this.id = id;
        this.title = title;
        this.address = address;
        this.guestCapacity = guestCapacity;
        this.rooms = rooms;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public int getGuestCapacity() {
        return guestCapacity;
    }

    public void setGuestCapacity(int guestCapacity) {
        this.guestCapacity = guestCapacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getRating() {
        return rating;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
