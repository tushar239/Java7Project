package test;

public class TestException extends RuntimeException {
    public TestException(String message, Exception e) {
        super(message, e);
    }
}
