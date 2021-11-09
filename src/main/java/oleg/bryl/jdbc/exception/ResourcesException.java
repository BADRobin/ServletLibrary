package oleg.bryl.jdbc.exception;

public class ResourcesException extends Exception {

    public ResourcesException(String message, Exception cause) {
        super(message, cause);
    }

    public ResourcesException(String message) {
        super(message);
    }
}

