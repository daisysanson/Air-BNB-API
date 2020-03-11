import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class Names {
    private int identificationNumber;
    private String name;
    private Date dateRegistered = new Date();
    @JsonIgnore
    private int noOfBookings;
    private boolean booked;

    public Names() {

    }

    public Names(int identificationNumber, String name, Date dateRegistered, boolean booked) {
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.dateRegistered = dateRegistered;
        this.booked = booked;
    }

    public int getidentificationNumber() {
        return identificationNumber;
    }

    public void setidentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }


    public boolean getBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getNoOfBookings() {
        return noOfBookings;
    }

    public void setNoOfBookings(int noOfBookings) {
        this.noOfBookings = noOfBookings;
    }
}

