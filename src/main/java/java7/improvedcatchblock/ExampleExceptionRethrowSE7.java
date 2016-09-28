package java7.improvedcatchblock;

import java.io.IOException;

//http://www.oracle.com/technetwork/articles/java/java7exceptions-486908.html --- Example 5

public class ExampleExceptionRethrowSE7 {
    /* 
    Java 6 style
    public static void main(String[] args) {
        try {
            demoRethrow();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

    private static void demoRethrow() throws Exception{
        try {
            throw new IOException("Error");
        } catch (Exception exception) { // IOException is wrapped by Exception. It forces you to add throws Exception. Java 7 allows to add throws IOException. 
            throw exception;
        }
        
    }
     
     */
    public static void main(String[] args) {
        try {
            demoRethrow();
        } catch (IOException exception) {
            System.err.println(exception.getMessage());// O/P: Error
        }
        
        try {
            demoRethrow1(); // O/P: java.lang.RuntimeException: java.io.IOException: Error
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }

    private static void demoRethrow() throws IOException{// allows 'throws IOException'. Doesn't force to use 'throws Exception' like Java6.
        try {
            throw new IOException("Error");
        } catch (Exception exception) {
            throw exception;
        }
        
    }
    private static void demoRethrow1() throws IOException{
        try {
            throw new IOException("Error");
        } catch (IOException exception) {
            // wrapping original exception IOException with sibling exception (RuntimeException).
            // wrapping it with a parent class (Exception) forces us to add 'throws Exception'.
            throw new RuntimeException(exception);
        }
        
    }
}
