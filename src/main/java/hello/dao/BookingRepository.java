package hello.dao;

import hello.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository  extends MongoRepository<Booking, String> {


}
