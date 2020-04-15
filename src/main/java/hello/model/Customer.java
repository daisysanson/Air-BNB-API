package hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Document(collection = "customers")
@RestController
public class Customer {

    @Id //this is the primary key field
    private String id;

    @Field(value="name")
    @NotNull
    private String name;

    @Field(value="date")
    private Date date = new Date();

    @Field(value="bookingConfirmed")
    @NotNull
    private Boolean bookingConfirmed;

    public Customer() {

    }

    public Customer(@JsonProperty("id") String id,
                    @JsonProperty("name") String name,
                    @JsonProperty("booking_confirmed") Boolean bookingConfirmed) {


        this.id = id;
        this.name = name;
        this.bookingConfirmed = bookingConfirmed;



}



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean isBookingConfirmed() {
        return bookingConfirmed;
    }

    public void setBookingConfirmed(Boolean bookingConfirmed) {
        this.bookingConfirmed = bookingConfirmed;
    }
}

