package java5.nio;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;

// http://www.java2s.com/Tutorials/Java/java.io/Reader/Java_Reader_read_CharBuffer_target_.htm
public class CharBufferTest {
    public static void main(String[] args) {

        String s = "tutorial from java2s.com";

        CharBuffer cb = CharBuffer.allocate(100);

        Reader reader = new StringReader(s);

        try {
            reader.read(cb);

            cb.flip();

            // print the char buffer
            System.out.println(cb.toString());

            reader.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
