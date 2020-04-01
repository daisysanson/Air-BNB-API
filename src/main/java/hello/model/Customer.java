package hello.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.util.Date;
import java.util.UUID;

@Document(collection = "customers")
    public class Customer {
    @Id
    private UUID id;
        @NotBlank
        private String name;
        @Indexed(direction = IndexDirection.ASCENDING)
        private Date dateRegistered = new Date();
        @NotNull
        private Boolean bookingConfirmed;

        public Customer(@JsonProperty("id") UUID id,
                        @JsonProperty("name") String name,
                        @JsonProperty("booking_confirmed") Boolean bookingConfirmed) {

            this.id = id;
            this.name = name;
            this.bookingConfirmed = bookingConfirmed;
        }

        public UUID getId() {
            return id;
        }

        public String getName() {
            return name;
        }


        public Date getDateRegistered() {
            return dateRegistered;
        }

        public Boolean isBookingConfirmed() {
            return bookingConfirmed;
        }

    }




