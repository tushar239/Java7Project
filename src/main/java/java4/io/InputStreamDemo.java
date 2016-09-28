package java4.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Tushar Chokshi @ 4/25/15.
 */


/*
http://www.tutorialspoint.com/java/io/java_io_inputstream.htm
http://tutorials.jenkov.com/java-io/inputstream.html

Stream means not necessarily just a file, but any stream of data in any form (byte[], file etc)
InputStream reads byte by byte from opened stream.
It mainly has below methods
- read() --- reads byte by byte
- read(byte[]) --- read data in byte array
- mark() --- Marks the current position in this input stream.
- reset() --- Repositions this stream to the position at the time the mark() was last called on this input stream.

InputStream can be surrounded by other input streams like BufferedInputStream, DataInputStream etc. Basically, they act as a decorator to main InputStream.
- FileInputStream
- BufferedInputStream
- ByteArrayInputStream
- DataInputStream
- FilterInputStream
- PushbackInputStream
- SequenceInputStream
- PipedInputStream
- ObjectInputStream

 */


public class InputStreamDemo {
    public static void main(String[] args) throws Exception {

        // http://www.tutorialspoint.com/java/io/inputstream_read.htm
        FileInputStreamDemo();
        System.out.println();
        BufferedInputStreamDemo();
        System.out.println();
        DataInputStreamDemo();
        System.out.println();
        ObjectInputStreamDemo();

    }

    private static void FileInputStreamDemo() {
        int i;
        char c;
  /*
            String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+current);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current dir using System:" +currentDir);
            */


        try (InputStream is = new FileInputStream("./Java7Project/src/main/resources/text.txt")) {
            System.out.println("Characters printed by FileInputStreamDemo():");
            // reads till the end of the stream
            while ((i = is.read()) != -1) {
                // converts integer to character
                c = (char) i;
                // prints character
                System.out.print(c);
            }
        } catch (Exception e) {
            // if any I/O error occurs
            e.printStackTrace();
        }
    }

    /*
    http://tutorials.jenkov.com/java-io/bufferedinputstream.html
    http://www.tutorialspoint.com/java/io/bufferedinputstream_read.htm

    Rather than read one byte at a time from the network or disk, the BufferedInputStream reads a larger block at a time.
    This is typically much faster, especially for disk access and larger data amounts.
    The BufferedInputStream creates a byte array internally, and attempts to fill the array by calling the InputStream.read(byte[]) methods on the underlying InputStream.

    You can set the buffer size to use internally by the BufferedInputStream. You provide the buffer size as a parameter to the BufferedInputStream constructor, like this:
    InputStream input = new BufferedInputStream(
                      new FileInputStream("c:\\data\\input-file.txt"),
                      8 * 1024
    );

    This example sets the internal buffer used by the BufferedInputStream (8 KB).
    It is best to use buffer sizes that are multiples of 1024 bytes. That works best with most built-in buffering in hard disks etc.

    If total bytes size is more than provided buffer size, then internally, size will be increased. It improves the performance if you provide the default size.


     */
    private static void BufferedInputStreamDemo() {
        try (InputStream fis = new FileInputStream("./Java7Project/src/main/resources/text.txt");
            BufferedInputStream bis = new BufferedInputStream(fis))
        {
            System.out.println("Characters printed by BufferedInputStreamDemo():");
            // read until a single byte is available
            while(bis.available()>0)
            {
                // read the byte and convert the integer to character
                char c = (char)bis.read();
                System.out.print(c);;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    http://tutorials.jenkov.com/java-io/datainputstream.html
    http://www.tutorialspoint.com/java/io/datainputstream_readfully.htm

    The DataInputStream class enables you to read Java primitives from InputStream's instead of only bytes.
    int    aByte   = input.read();
    int    anInt   = input.readInt();
    float  aFloat  = input.readFloat();
    double aDouble = input.readDouble();
    //etc.
     */
    private static void DataInputStreamDemo() {
        try (InputStream fis = new FileInputStream("./Java7Project/src/main/resources/text.txt");
             DataInputStream dis = new DataInputStream(fis);) {
            /*System.out.println("Characters printed by DataInputStreamDemo():");
            System.out.print(dis.readChar());
            System.out.print(dis.readChar());
            System.out.print(dis.readChar());
            System.out.print(dis.readInt());

            System.out.println();
            */
            System.out.println("Characters printed by DataInputStreamDemo():");
            while(dis.available() > 0) {
                byte[] buf = new byte[dis.available()];
                dis.readFully(buf); // read the full data into the buffer
                for (byte b:buf)
                {
                    char c = (char)b;
                    System.out.print(c);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ObjectInputStreamDemo() {
        class SomeClass implements Serializable {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        SomeClass someClass = new SomeClass();
        someClass.setName("Tushar");

        try {
            System.out.println("Class printed by ObjectInputStreamDemo():");
            SomeClass someClass1 = (SomeClass)deserialize(serialize(someClass));
            System.out.println(someClass1.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private static byte[] serialize(Object obj) throws IOException {
        // Serializing in ByteArray
        ByteArrayOutputStream out = new ByteArrayOutputStream();// You can serialize to file also using FileOutputStream also.
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);

        // Serializing in File
        FileOutputStream fileOutputStream = new FileOutputStream("./Java7Project/src/main/resources/serialize.txt");
        ObjectOutputStream os1 = new ObjectOutputStream(fileOutputStream);
        os1.writeObject(obj);


        return out.toByteArray();
    }
    private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
