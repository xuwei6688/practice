package nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author xuwei
 * @Date 2020/12/6
 * @Version V1.0
 **/
public class FileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello filebuffer";
        FileOutputStream fos = new FileOutputStream("file01.txt");

        //通过流获取对应的Channel
        FileChannel channel = fos.getChannel();

        //创建缓冲区，并将数据写入缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

        //反转
        buffer.flip();

        //将缓冲区的数据写入Channel
        channel.write(buffer);
        fos.close();
    }
}
