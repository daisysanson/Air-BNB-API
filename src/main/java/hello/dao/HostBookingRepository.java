package hello.dao;

import hello.model.Booking;
import hello.model.HostBooking;
import org.apache.catalina.Host;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HostBookingRepository  extends MongoRepository<HostBooking, String> {
}
