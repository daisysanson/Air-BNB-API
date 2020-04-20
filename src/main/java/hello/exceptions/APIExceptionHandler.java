package hello.exceptions;

import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoSocketReadException;
import com.mongodb.MongoTimeoutException;
import hello.controller.CustomerController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    static Logger log = Logger.getLogger(CustomerController.class);

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

    @ExceptionHandler(value = MultiErrorException.class)
    public ResponseEntity<ApiMultiErrorException> handleMultiErrors(MultiErrorException e) {

        ApiMultiErrorException apiException = new ApiMultiErrorException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z")),
                e.getErrors()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
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


}
