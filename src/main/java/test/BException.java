package test;

public class BException extends RuntimeException {
    public BException(String message, Exception e) {
        super(message, e);
    }
}
