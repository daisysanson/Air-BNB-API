package hello.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.UUID;

    public class Customer {
        private UUID id;
        //        @NotBlank //this has to be filled in as it will throw an error
        private String name;
        private Date dateRegistered = new Date();
        private boolean bookingConfirmed;

        public Customer(@JsonProperty("id") UUID id,
                        @JsonProperty("name") String name,
                        @JsonProperty("booking_confirmed") boolean bookingConfirmed) {

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

        public boolean isBookingConfirmed() {
            return bookingConfirmed;
        }

    }




