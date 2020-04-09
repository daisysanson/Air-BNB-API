package hello.exceptions;

public class ApiGlobal extends Exception {

    public ApiGlobal(String message) {
        super(message);
    }

    public ApiGlobal(Throwable cause) {
        super(cause);
    }


}
