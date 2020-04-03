package hello.exceptions;

import org.json.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.Response;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value  = ApiRequestException.class ) //this exception will be fed into calss below
        public ResponseEntity <Object> handleApiRequestExtension(ApiRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

         APIException apiException = new APIException( //handles payload information
                e.getMessage(),
                e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))

        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
    }


