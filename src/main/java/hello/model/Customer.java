package hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;


@QueryEntity
@Document(collection = "customers")
public class Customer {

    @Id
    @NotNull
    private String id;

    @Field(value = "name")
    @NotNull
    private String name;

    @Field(value = "userName")
    @NotNull
    private String userName;

    @Field(value = "date")
    private Date date = new Date();

    @Field(value = "bookingConfirmed")
    @NotNull
    private Boolean bookingConfirmed;


    public Customer() {
    }

    public Customer(@JsonProperty("id") String id,
                    @JsonProperty("name") String name,
                    @JsonProperty("user_name") String userName,
                    @JsonProperty("booking_confirmed") Boolean bookingConfirmed) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.bookingConfirmed = bookingConfirmed;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Boolean getBookingConfirmed() {
        return bookingConfirmed;
    }

    public void setBookingConfirmed(Boolean bookingConfirmed) {
        this.bookingConfirmed = bookingConfirmed;
    }

}
