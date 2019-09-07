package java5.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// http://www.java2s.com/Code/JavaAPI/java.nio/ByteBufferlimit.htm
public class ByteBufferTest {
    public static void main(String[] args) {
        String phrase = new String("www.java2s.com");

        File aFile = new File("test.txt");
        FileOutputStream outputFile = null;
        try {
            outputFile = new FileOutputStream(aFile, true);
            System.out.println("File stream created successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
        }

        FileChannel outChannel = outputFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("New buffer:           position = " + buf.position() + "\tLimit = " + buf.limit() + "\tcapacity = " + buf.capacity());

        // At this point: position=0, limit=1024, capacity=1024
        
        // Load the data into the buffer
        for (char ch : phrase.toCharArray()) {
            buf.putChar(ch); // every time you put/get from buffer, position increments.
        }
        
        // At this point: position=28, limit=1024, capacity=1024
        
        System.out.println("Buffer after loading: position = " + buf.position() + "\tLimit = " + buf.limit() + "\tcapacity = " + buf.capacity());
        
        buf.flip(); // After flip: position=0, limit=28, capacity=1024 (limit=position and position=0)

        System.out.println("Buffer after flip:    position = " + buf.position() + "\tLimit = " + buf.limit() + "\tcapacity = " + buf.capacity());

        buf.putChar('z'); // As position is set to 0 now because of flip(), z will replace first w.
        
        System.out.println("Buffer after adding z:    position = " + buf.position() + "\tLimit = " + buf.limit()+ "\tcapacity = " + buf.capacity());
        

        //buf.flip();
        
        //System.out.println("Buffer after flip:    position = " + buf.position() + "\tLimit = " + buf.limit() + "\tcapacity = " + buf.capacity());
        
        buf.rewind(); // position is set to 0
        
        System.out.println("Buffer after rewind:    position = " + buf.position() + "\tLimit = " + buf.limit()+ "\tcapacity = " + buf.capacity());

        
        for (int i = 0; i < buf.limit(); i++)
            System.out.print((char) buf.get());
        //System.out.println(buf.getChar(30));
        System.out.println();
        
        try {
            outChannel.write(buf);
            outputFile.close();
            System.out.println("Buffer contents written to file.");
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
