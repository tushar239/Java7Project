package java5.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Tushar Chokshi @ 6/7/15.
 */
public class ChannelTest {
    public static void main(String[] args) throws Exception {

        /*
        Stream oriented:
        If you read code of BufferedReader's readLine() method, it actually reads byte by byte or char by char from the stream and fills up StringBuffer and returns stringBuffer.toString().
        It blocks the thread until entire line is read.

        Channel and Buffer oriented:
        in nio, you use channel to read data from file and put it into fixed size buffer.
        It is not necessary that first time filled up buffer will contain entire line. It will just contain the data of fixed bytes.
        It's your responsibility to check whether buffer has enough required data that you want. If not, you clear the buffer and fill up from the channel again.
        This is a kind of non-blocking because reading a chunk of fixed size data into buffer takes lesser time than reading it byte by byte from the stream and buffering it.

        https://dzone.com/articles/java-nio-vs-io


        */

        //testBufferReadWrite();

        testChannelReadWrite();

    }

    private static void testChannelReadWrite() throws Exception {
        {
            RandomAccessFile aFile = new RandomAccessFile("./Java7Project/src/main/resources/text.txt", "rw");
            FileChannel channel = aFile.getChannel();

            System.out.println("Before adding more to channel, channel size is:" + channel.size());
            ByteBuffer buf = ByteBuffer.allocate(48);
            int numberOfBytesInBuffer = channel.read(buf);

            System.out.println("Before getting the bytes from buffer, current buf position:" + buf.position());
            System.out.println("Before getting the bytes from buffer, current buf limit:" + buf.limit());


            System.out.println();
            buf.flip();
            if (numberOfBytesInBuffer > -1) {
                while (buf.hasRemaining()) { // checks position < limit
                    System.out.print((char) buf.get());
    //            System.out.println("current buf position:"+buf.position());
    //            System.out.println("current buf limit:" + buf.limit());

                }
            }

            System.out.println();
            System.out.println("After getting the bytes from buffer, current buf position:" + buf.position());
            System.out.println("After getting the bytes from buffer, current buf limit:" + buf.limit());

            channel.close();
            aFile.close();
        }
        {
            ByteBuffer buf = ByteBuffer.allocate(1024);
            {
                RandomAccessFile aFile = new RandomAccessFile("./Java7Project/src/main/resources/text.txt", "rw");
                FileChannel channel = aFile.getChannel();

                channel.read(buf); // buf= hi, how r u?
                System.out.println("After reading the file, buf position:" + buf.position());
                System.out.println("After reading the file, buf limit:" + buf.limit());


                buf.put("z".getBytes());// buf= hi, how r u?z

                System.out.println();
                System.out.println("After adding more to buf, buf position:" + buf.position());
                System.out.println("After adding more to buf, buf limit:" + buf.limit());

                buf.limit(buf.position());
                buf.flip();
                while(buf.hasRemaining()) {
                    channel.write(buf); // buf= hi, how r u?hi, how r u?z
                }

                aFile.close();
            }

        }


    }

    private static void testBufferReadWrite() throws IOException {
        // reading the data from buffer --- no inputstream/reader is required. Use the same buffer by flipping (resetting the position to 0) to read the data.
        ByteBuffer buf = writeReadBuffer();

        // test buffer.compact()
        System.out.println();
        System.out.println();
        System.out.println("Test buffer.compact()");
        buf.flip();// limit=position and position=0
        if(buf.hasRemaining()) {
            System.out.println((char)buf.get());
        }
        buf.compact();// position=limit - position, limit=capacity, mark=-1
        buf.putChar('W'); // O/P:   i 123 how r u? W
        buf.flip();
        while(buf.hasRemaining()){ // checks position < limit
            System.out.print((char) buf.get());
        }


        // test buffer.clear()
        System.out.println();
        System.out.println();
        System.out.println("Test buffer.clear()");
        buf.clear(); // position = 0, limit = capacity, mark = -1. Note: Data is not removed from the buffer, just indexes are reset.
        System.out.println("After clearing the buffer:");
        while(buf.hasRemaining()){ // checks position < limit
            System.out.print((char) buf.get());
        }

        System.out.println();
    }

    private static ByteBuffer writeReadBuffer() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("./Java7Project/src/main/resources/text.txt", "rw");

        // getting a handle of channel from a file
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        // writing file data from channel to buffer --- no outputstream/write is required
        int bytesRead = inChannel.read(buf); // at this stage, position=total number of bytes in the file and limit=buffer's capacity(48)


        System.out.println("Read " + bytesRead);
        // flips buffer from writing mode to reading mode
        buf.flip(); // set limit=position and position=0. Means starts reading the data from 0th location in buffer and read till its limit.

        while(buf.hasRemaining()){ // checks position < limit
            System.out.print((char) buf.get()); // position and limit increments as u do get()
        }

        // at this stage, both position and limit = total number of bytes in the file

        aFile.close();
        return buf;
    }

}
