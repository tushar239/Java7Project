package java5.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author Tushar Chokshi @ 6/7/15.
 */
public class ChannelToChannelTransfer {
    public static void main(String[] args) throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("./Java7Project/src/main/resources/fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("./Java7Project/src/main/resources/toFile.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count    = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
        fromChannel.close();
        fromFile.close();
        toChannel.close();
        toFile.close();
    }
}
