package oleg.bryl.jdbc.exception;

public class ConnectionException extends Exception{
    public ConnectionException(String message , Exception cause){
        super(message , cause);
    }
}
