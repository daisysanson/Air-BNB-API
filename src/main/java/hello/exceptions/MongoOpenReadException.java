package hello.exceptions;

import com.mongodb.MongoException;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MongoOpenReadException extends MongoException {


    public MongoOpenReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
