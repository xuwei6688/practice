package com.xu.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 获取通道的两种方式：
 * 1.通过 FileInputStream、FileOutputStream、Socket、ServerSocket 等获取
 * 2.直接调用通道的 open 方法获取，例如：ileChannel.open
 */
public class ChannelTest {
    @Test
    public void test1() throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream("/Users/xuwei/IdeaProjects/practice/1.jpeg");
            fos = new FileOutputStream("/Users/xuwei/IdeaProjects/practice/2.jpeg");

            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            //将Channel中的字节读入ByteBuffer中，全部读完返回-1
            while (inChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }



    /**
     * java 中的 transferFrom 和 transferTo 对应 sendfile 系统调用。
     * sendfile 系统调用是真正的零拷贝，数据通过 DMA 拷贝到内核缓冲区，再通过 DMA 拷贝到协议栈，经历两次 DMA 拷贝，
     * 不需要 CPU 参与
     */
    @Test
    public void test2() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/xuwei/IdeaProjects/practice/1.jpeg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/xuwei/IdeaProjects/practice/4.jpeg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);
//        outChannel.transferFrom(inChannel, 0, inChannel.size());
        outChannel.close();
        inChannel.close();
    }

    /**
     * java 的 channel.map 实际对应了 mmap 系统调用
     */
    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("/Users/xuwei/IdeaProjects/practice/1.jpeg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("/Users/xuwei/IdeaProjects/practice/6.jpeg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        MappedByteBuffer inMapped = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapped = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] buffer = new byte[inMapped.limit()];
        inMapped.get(buffer);
        outMapped.put(buffer);

        outChannel.close();
        inChannel.close();
    }
}
