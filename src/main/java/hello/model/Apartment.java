package hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Document(collection = "apartments")


public class Apartment {
    @Id
    private String id;

    @Field(value = "title")
    @NotNull
    private String title;

    @Field(value = "location")
    @NotNull
    private String location;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Field(value = "occupiedDateStart")
    private Date occupiedDateStart;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Field(value = "occupiedDateEnd")
    private Date occupiedDateEnd;

    @Field(value = "guestCapacity")
    private int guestCapacity;


    public Apartment() {

    }

    public Apartment(String id, @JsonProperty("title") String title,
                     @JsonProperty("location") String location,
                     @JsonProperty("occupied_start_date") Date occupiedDateStart,
                     @JsonProperty("occupied_end_date") Date occupiedDateEnd,
                     @JsonProperty("guest_capacity") int guestCapacity) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.occupiedDateStart = occupiedDateStart;
        this.occupiedDateEnd = occupiedDateEnd;
        this.guestCapacity = guestCapacity;
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

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date getOccupiedDateStart() {
        return occupiedDateStart;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setOccupiedDateStart(Date occupiedDateStart) {
        this.occupiedDateStart = occupiedDateStart;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date getOccupiedDateEnd() {
        return occupiedDateEnd;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setOccupiedDateEnd(Date occupiedDateEnd) {
        this.occupiedDateEnd = occupiedDateEnd;
    }

    public int getGuestCapacity() {
        return guestCapacity;
    }

    public void setGuestCapacity(int guestCapacity) {
        this.guestCapacity = guestCapacity;
    }

}




//
//    public class JsonDateSerializer extends JsonSerializer<Date> {
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//        @Override
//        public void serialize(final Date date, final JsonGenerator gen, final SerializerProvider provider) throws IOException, JsonProcessingException {
//
//            String dateString = format.format(date);
//            gen.writeString(dateString);
//        }
//
//    }




