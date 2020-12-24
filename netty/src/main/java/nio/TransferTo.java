package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author xuwei
 * @Date 2020/12/6
 * @Version V1.0
 **/
public class TransferTo {
    public static void main(String[] args) throws IOException {
//       test2();
        test3();
    }

    public static void test1() throws IOException{
        FileInputStream fis = new FileInputStream("1.jpeg");
        FileOutputStream fos = new FileOutputStream("2.jpeg");

        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        inChannel.transferTo(0, inChannel.size(), outChannel);

        outChannel.close();
        inChannel.close();
    }

    public static void test2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpeg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpeg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();

    }

    public static void test3() throws IOException{
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.putInt(1);

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        readOnlyBuffer.flip();
        System.out.println(readOnlyBuffer.getInt());

        readOnlyBuffer.putInt(3);
    }
}
