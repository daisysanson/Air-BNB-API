package hello.exceptions;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Method;
import java.time.ZonedDateTime;

public class JsonErrors {

        private final MethodParameter message;
        private final HttpStatus httpStatus;
        private ZonedDateTime timestamp;
        private BindingResult result;



        public JsonErrors(MethodParameter message, HttpStatus httpStatus, ZonedDateTime timestamp, BindingResult result) {
            this.message = message;
            this.httpStatus = httpStatus;
            this.timestamp = timestamp;
            this.result = result;
        }


        public MethodParameter getMessage() {
            return message;
        }

        public BindingResult getResult() {
            return result;
        }

        public void setResult(BindingResult result) {
            this.result = result;
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


