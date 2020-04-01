package hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String name;
    private Date date;
    private boolean bookingConfirmed;

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

    public boolean isBookingConfirmed() {
        return bookingConfirmed;
    }

    public void setBookingConfirmed(boolean bookingConfirmed) {
        this.bookingConfirmed = bookingConfirmed;
    }
}

