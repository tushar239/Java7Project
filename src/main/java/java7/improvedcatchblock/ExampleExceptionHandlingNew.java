package java7.improvedcatchblock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
// http://www.oracle.com/technetwork/articles/java/java7exceptions-486908.html --- Example 2

// Smart catch block avoids using multiple catch blocks for different types of exception.
// You can concatenate all possibly thrown exceptions using pipe (|) in one catch block.

public class ExampleExceptionHandlingNew {
    public static void main( String[] args )
    {
     try {
         URL url = new URL("http://www.yoursimpledate.server/");
         BufferedReader reader = new BufferedReader(
             new InputStreamReader(url.openStream()));
         String line = reader.readLine(); // readLine() has 'throws IOException'
         SimpleDateFormat format = new SimpleDateFormat("MM/DD/YY");
         Date date = format.parse(line); // parse(...) method has 'throws ParseException'
     }
     // catch block allows you add only those exceptions which are thrown from try block + it allows to add any unchecked exceptions too.
     // e.g. if you comment out last line that uses parse method, compiler will complain that you can not use ParseException in catch block.
     catch(ParseException | IOException | NullPointerException exception) {
         // handle our problems here.
     }
    }
}
