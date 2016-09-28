package java4.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/*
An InputStreamReader is a bridge from byte streams (InputStream) to character streams (Reader).

InputStreams can read bytes from the stream.
Readers can read characters/lines from the stream.

FileReader extends InputStreamReader implements Reader extends Closeable extends AutoCloseable
StringReader does not extend InputStreamReader because there is no conversion from byte stream to character stream. It reads string directly. It implements Reader directly.

*/

public class ReaderDemo {
    public static void main(String[] args) {
        System.out.println("FileReader and BufferedReader Example...");
        fileReaderDemo();

        System.out.println();
        System.out.println();

        System.out.println("StringReader Example...");
        stringReaderDemo();
    }

    private static void stringReaderDemo() {
        try(StringReader stringReader = new StringReader("hi, how r u?")) {
            int c;
            while ((c=stringReader.read()) != -1) {
                System.out.print((char)c);
            }

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    private static void fileReaderDemo() {
        System.out.println("read() example");

        // An InputStreamReader is a bridge from byte streams to character streams: It
        // reads bytes and decodes them into characters using a specified charset
        //InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("sdf"));
        try(InputStreamReader inputStreamReader = new FileReader("./Java7Project/src/main/resources/text.txt");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            int i;
            while((i=bufferedReader.read()) != -1) {
                System.out.print((char)i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("readLine() example");
        try(InputStreamReader inputStreamReader = new FileReader("./Java7Project/src/main/resources/text.txt");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                System.out.print(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
