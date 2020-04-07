package hello.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

// @ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ApiRequestException.class)
    //this exception will be fed into calss below //all exceptions of this type go to hear
    public ResponseEntity<Object> handleBadRequest(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        APIException apiException = new APIException( //handles payload information
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))

        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class) //this exception will be fed into calss below
    public ResponseEntity<Object> handleNotFound(NotFoundException e) {
        HttpStatus notFound1 = HttpStatus.NOT_FOUND;

        NotFound notFound = new NotFound(
                e.getMessage(),
                notFound1,
                ZonedDateTime.now(ZoneId.of("Z")

        ));
        return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);


    }


}



