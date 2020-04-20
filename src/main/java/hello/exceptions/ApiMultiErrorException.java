package hello.exceptions;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;
import java.util.List;


public class ApiMultiErrorException extends APIException {
    private final List<String> errors;


    public ApiMultiErrorException(String message, HttpStatus httpStatus, ZonedDateTime timestamp, List<String> errors) {
        super(message, httpStatus, timestamp);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}

