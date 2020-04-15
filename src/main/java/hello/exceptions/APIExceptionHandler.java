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
            public ResponseEntity<APIException> handleConverterErrors(HttpMessageNotReadableException e) {
                HttpStatus badRequest = HttpStatus.NOT_ACCEPTABLE;
                log.debug("Hello Wold");
                log.error("exception caught: " + e);

                APIException apiException = new APIException( //handles payload information
                        "Please enter data in correct format :" ,
                        badRequest,
                        ZonedDateTime.now(ZoneId.of("Z")));

                return new ResponseEntity(apiException, HttpStatus.NOT_ACCEPTABLE);

            }

            @ExceptionHandler(value = BadRequestException.class)
            public ResponseEntity<APIException> handleBadRequest(BadRequestException e) {
                HttpStatus badRequest = HttpStatus.BAD_REQUEST;
                log.error("exception caught: " + e);

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
            public ResponseEntity<APIException> handleNotFound(MongoSocketOpenException e) {
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
            public ResponseEntity<APIException> handleNotFound(MongoTimeoutException e) {
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
            public ResponseEntity<APIException> handleNotFound(MongoSocketReadException e) {
                HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
                log.error("exception caught: " + e);

                APIException apiException = new APIException(
                        e.getMessage(),
                        internalServerError,
                        ZonedDateTime.now(ZoneId.of("Z")

                        ));
                return new ResponseEntity(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            @ExceptionHandler(value = Exception.class)
            public ResponseEntity<ApiGlobalException> handleAllExceptions(Exception e) {
                log.error("exception caught: " + e);
                ApiGlobalException apiGlobalException = new ApiGlobalException(
                        "Something went wrong",
                        HttpStatus.I_AM_A_TEAPOT,
                        ZonedDateTime.now(ZoneId.of("Z")));



                return new ResponseEntity<>(apiGlobalException, HttpStatus.I_AM_A_TEAPOT);


            }
        }
