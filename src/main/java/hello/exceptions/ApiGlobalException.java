package hello.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiGlobalException {
    public String message;
    private final HttpStatus httpStatus;
    private ZonedDateTime timestamp;
     private Throwable code;


    public ApiGlobalException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;


    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;

    }

}