package hello.exceptions;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }



    public ForbiddenException(Throwable cause) {
        super(cause);
    }
}
