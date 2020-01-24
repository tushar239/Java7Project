package test;

public class AException extends RuntimeException {
    public AException(String message, Exception e) {
        super(message, e);
    }
}
