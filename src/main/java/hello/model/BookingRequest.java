package hello.model;

public class BookingRequest {
    public String name;
    public Apartment2 apartment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Apartment2 getApartment() {
        return apartment;
    }

    public void setApartment(Apartment2 apartment) {
        this.apartment = apartment;
    }
}
