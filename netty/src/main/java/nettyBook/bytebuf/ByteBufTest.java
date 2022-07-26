package nettyBook.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.junit.Test;

import java.nio.ByteBuffer;

public class ByteBufTest {
    /**
     * 堆缓冲区
     */

    public static void heap() {
        ByteBuf byteBuf = Unpooled.buffer();
        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            int offset = byteBuf.arrayOffset() + byteBuf.readerIndex();
            int length = byteBuf.readableBytes();
//            handleArray(array, offset, length);
        }
    }

    /**
     * 直接缓冲区
     */
    public static void direct() {
        ByteBuf byteBuf = Unpooled.directBuffer();
        if (!byteBuf.hasArray()) {
            int length = byteBuf.readableBytes();

            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array);
        }
    }

    /**
     * 复合缓冲区1：jdk实现，数组copy
     */
    public static void composite1() {
        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(100);

        ByteBuffer message = ByteBuffer.allocate(header.remaining() + body.remaining());
        //put方法会将ByteBuffer中剩余的内容存到message中
        message.put(header);
        message.put(body);
        message.flip();
    }

    /**
     * 复合缓冲区
     */
    @Test
    public  void composite2() {
        ByteBuf header = Unpooled.wrappedBuffer("header=1".getBytes());
        ByteBuf body = Unpooled.wrappedBuffer("body=2".getBytes());

        CompositeByteBuf message = Unpooled.compositeBuffer();
        message.addComponents(header, body);

        //移除第一个ByteBuf
        message.removeComponent(0);

        for (ByteBuf byteBuf : message) {
            System.out.println(byteBuf.toString());
        }
    }
}
