package hello.exceptions;

import java.util.List;

public class MultiErrorException extends RuntimeException {
    private List<String> errors;

    public MultiErrorException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
