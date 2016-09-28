package java7.improvedcatchblock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// http://www.oracle.com/technetwork/articles/java/java7exceptions-486908.html --- Example 7

// Be aware that this feature works only on classes that have implemented the AutoCloseable interface.

public class ExampleTryResources {
    public static void main(String[] args) {
        // No need to add finally block to close reader instance.
        // wrapping a resource with try(...) will take care of that. 
        // Resources wrapped inside try(...) should be of type AutoCloseable interface
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://www.yoursimpledate.server/").openStream()))) {
            String line = reader.readLine();
            SimpleDateFormat format = new SimpleDateFormat("MM/DD/YY");
            Date date = format.parse(line);
        } catch (ParseException | IOException exception) {
            // handle I/O problems.
        }
    }
}
