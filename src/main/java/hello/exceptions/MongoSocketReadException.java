package hello.exceptions;

import com.mongodb.MongoException;

public class MongoSocketReadException extends MongoException {

    public MongoSocketReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
