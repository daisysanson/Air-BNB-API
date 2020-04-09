package hello.exceptions;

import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoSocketReadException;
import com.mongodb.MongoTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = ApiGlobal.class)
    public ResponseEntity<ApiGlobalException> handleAllExceptions(ApiGlobal e) {
        ApiGlobalException apiGlobalException = new ApiGlobalException(
                "Something went wrong",
                HttpStatus.I_AM_A_TEAPOT,
                ZonedDateTime.now(ZoneId.of("Z")));


        return new ResponseEntity<>(apiGlobalException, HttpStatus.I_AM_A_TEAPOT);


    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<APIException> handleBadRequest(BadRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        APIException apiException = new APIException( //handles payload information
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))

        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class) //this exception will be fed into calss below
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
}