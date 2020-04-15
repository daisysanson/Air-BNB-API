package hello.exceptions;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoSocketReadException;
import com.mongodb.MongoTimeoutException;
import hello.controller.CustomerController;
import org.apache.log4j.Logger;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.crossstore.ChangeSetPersister;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.jws.WebResult;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@ControllerAdvice
public class APIExceptionHandler {

    static Logger log = Logger.getLogger(CustomerController.class);

    @ExceptionHandler(value = InvalidFormatException.class)
    public ResponseEntity<APIException> handleConverterErrors(InvalidFormatException e) {
        HttpStatus badRequest = HttpStatus.NOT_ACCEPTABLE;
        log.debug("Hello Wold");

        APIException apiException = new APIException( //handles payload information
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")))
                ;

        return new ResponseEntity(apiException, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(value = HttpClientErrorException.BadRequest.class)
    public ResponseEntity<APIException> handleBadRequest(HttpClientErrorException.BadRequest e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        APIException apiException = new APIException( //handles payload information
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))


        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = ChangeSetPersister.NotFoundException.class) //this exception will be fed into calss below
    public ResponseEntity<APIException> handleNotFound(NotFoundException e) {
        HttpStatus notFound1 = HttpStatus.NOT_FOUND;

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
        APIException apiException = new APIException(
                e.getMessage(),
                nullPointer,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = MongoSocketOpenException.class)
    public ResponseEntity<APIException> handleNotFound(MongoSocketOpenException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        APIException apiException = new APIException(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z")

                ));
        return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = MongoTimeoutException.class)
    public ResponseEntity<APIException> handleNotFound(MongoTimeoutException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        APIException apiException = new APIException(
                "Server timed out!",
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z")

                ));
        return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = MongoSocketReadException.class)
    public ResponseEntity<APIException> handleNotFound(MongoSocketReadException e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

        APIException apiException = new APIException(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z")

                ));
        return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);


    }

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ApiGlobalException> handleAllExceptions(Exception e) {
//        ApiGlobalException apiGlobalException = new ApiGlobalException(
//                "Something went wrong",
//                HttpStatus.I_AM_A_TEAPOT,
//                ZonedDateTime.now(ZoneId.of("Z")));
//
//
//        return new ResponseEntity<>(apiGlobalException, HttpStatus.I_AM_A_TEAPOT);
//
//
//    }
}
