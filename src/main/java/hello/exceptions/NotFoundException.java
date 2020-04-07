package hello.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
     public NotFoundException(Throwable cause){
        super(cause);
     }


}
