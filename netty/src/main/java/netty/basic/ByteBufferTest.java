package netty.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
        m5();
    }

    //支撑数组
    private static void m1() {
        //UnpooledHeapByteBuf

        ByteBuf byteBuf = Unpooled.wrappedBuffer("hello".getBytes());
        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            //arrayOffset() 返回支撑数组中表示 buffer 的第一个元素的偏移量
            int offset = byteBuf.arrayOffset() + byteBuf.readerIndex();
            int length = byteBuf.readableBytes();
            System.out.println(offset);
            System.out.println(length);
        }
    }

    /**
     * 直接缓冲区
     * 优点：避免了往jvm中拷贝
     * 缺点：1.分配内存和释放都比较昂贵 2.如果需要处理数据，需要往堆中复制，还是会有性能开销
     */
    private static void m2() {
        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeBytes("hello".getBytes());

        if (!byteBuf.hasArray()) {
            int length = byteBuf.readableBytes();
            byte[] array = new byte[length];
            byteBuf.getBytes(byteBuf.readerIndex(), array);
            System.out.println(new String(array));
        }
    }

    /**
     * jdk组合两个ByteBuffer
     */
    private static void m3() {
        ByteBuffer header = ByteBuffer.allocate(5);
        header.put("12345".getBytes());
        header.flip();

        ByteBuffer body = ByteBuffer.allocate(5);
        body.put("abcde".getBytes());
        body.flip();

        //需要开辟新的ByteBuffer，然后将分别将两个ByteBuffer中的内容put进去，这个put是需要复制的
        ByteBuffer message = ByteBuffer.allocate(header.remaining() + body.remaining());
        message.put(header);
        message.put(body);
        message.flip();

        byte[] bytes = new byte[10];
        message.get(bytes);
        System.out.println(new String(bytes));
    }

    /**
     * CompositeByteBuf可以组合多个ByteBuf，组成一个视图
     */
    private static void m4() {
        ByteBuf head = Unpooled.wrappedBuffer("12345".getBytes());
        ByteBuf body = Unpooled.wrappedBuffer("abcde".getBytes());

        CompositeByteBuf message = Unpooled.compositeBuffer();
        message.addComponents(true, head, body);

        //访问数据
        byte[] bytes = new byte[message.readableBytes()];
        message.getBytes(message.readerIndex(), bytes);
        System.out.println(new String(bytes));
    }

    private static void m5() {
        ByteBuf byteBuf = Unpooled.wrappedBuffer("12345\r678".getBytes());
        int index = byteBuf.indexOf(0, byteBuf.readableBytes(), (byte) '3');
        System.out.println(index);

        //下面这两种寻找回车的方式是一样的，indexOf内部也是调用的forEachByte(ByteProcessor processor)
        int y = byteBuf.indexOf(0, byteBuf.readableBytes(), (byte) '\r');
        System.out.println(y);

        int i = byteBuf.forEachByte(ByteProcessor.FIND_CR);
        System.out.println(i);

    }
}
