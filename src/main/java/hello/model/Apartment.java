package hello.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Document(collection = "apartments")
@RestController

public class Apartment {
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

    public Apartment(){

    }

    public Apartment(String id, @JsonProperty("title")String title,
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


    public class JsonDateDeserializer extends JsonDeserializer<Date> {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        @Override
        public Date deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken().equals(JsonToken.VALUE_STRING)) {
                try {
                    Date date = format.parse(jp.getText().toString());
                    return date;
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
            }
            return null;
        }

    }
}






