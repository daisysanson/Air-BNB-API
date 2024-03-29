package hello.exceptions;

import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoSocketReadException;
import com.mongodb.MongoTimeoutException;
//import hello.controller.CustomerController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class APIExceptionHandler {

    static Logger log = Logger.getLogger(APIExceptionHandler.class);


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<APIException> handleHttpNotReadableException(HttpMessageNotReadableException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        APIException apiException = new APIException( //handles payload information
                "Please enter data in correct format :",
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity(apiException, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<APIException> handleBadRequest(BadRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        APIException apiException = new APIException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))


        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<APIException> handleForbiddenRequest(ForbiddenException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        APIException apiException = new APIException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))


        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<APIException> handleNotFound(NotFoundException e) {
        HttpStatus notFound1 = HttpStatus.NOT_FOUND;
        log.error("exception caught: " + e);

        APIException apiException = new APIException(
                e.getMessage(),
                notFound1,
                ZonedDateTime.now(ZoneId.of("Z")

                ));
        return new ResponseEntity(apiException, HttpStatus.NOT_FOUND);


    }


    @ExceptionHandler(value = MongoException.class)
    public ResponseEntity<APIException> handleMongoException(MongoException e) {
        HttpStatus nullPointer = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("exception caught: " + e);
        APIException apiException = new APIException(
                e.getMessage(),
                nullPointer,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = MongoSocketOpenException.class)
    public ResponseEntity<APIException> handleMongoOpenSocketException(MongoSocketOpenException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("exception caught: " + e);

        APIException apiException = new APIException(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z")

                ));
        return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = MongoTimeoutException.class)
    public ResponseEntity<APIException> handleMongoTimeoutException(MongoTimeoutException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("exception caught: " + e);

        APIException apiException = new APIException(
                "Server timed out!",
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z")

                ));
        return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = MongoSocketReadException.class)
    public ResponseEntity<APIException> handleMongoReadException(MongoSocketReadException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("exception caught: " + e);

        APIException apiException = new APIException(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z")

                ));
        return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final Throwable throwable, final Model model) {
        log.error("Exception during execution of SpringSecurity application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<APIException> handleAllExceptions(Exception e) {
        log.error("exception caught: " + e);
        APIException apiException = new APIException(
                "Something has gone wrong",
                HttpStatus.I_AM_A_TEAPOT,
                ZonedDateTime.now(ZoneId.of("Z")));


        return new ResponseEntity(apiException, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<APIException> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("exception caught: " + e);
        APIException apiException = new APIException(
                "Please ensure all fields are correct and completed",
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity(apiException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException e) {

        Map<String, String> apiException = new HashMap<>();
        log.error("exception caught: " + e);
        e.getBindingResult().getFieldErrors().forEach(error -> {
                    if (apiException.containsKey(error.getField())) {
                        apiException.put(error.getField(), String.format("%s, %s", apiException.get(error.getField()), error.getDefaultMessage()));

                    } else {
                        apiException.put(error.getField(), error.getDefaultMessage());

                    }

                }

        );

        return new ResponseEntity(apiException, HttpStatus.BAD_REQUEST);

    }

}


