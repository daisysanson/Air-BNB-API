package hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

    public class Customer {
        private UUID id;
        @NotNull
        private String name;
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




