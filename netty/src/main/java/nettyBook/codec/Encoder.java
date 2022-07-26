package nettyBook.codec;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Encoder {
    //ByteToMessageDecoder
    public static class ToIntegerDecoder extends ByteToMessageDecoder {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            //对编码器和解码器来说，一旦消息被编码或解码，引用将自动释放
            if (in.readableBytes() >= 4) {
                out.add(in.readInt());
            }
        }
    }

    @Test
    public void test1() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeInt(i);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new ToIntegerDecoder());
        assertTrue(channel.writeInbound(buf));
        assertTrue(channel.finish());

        for (int i = 0; i < 10; i++) {
            Integer result = channel.readInbound();
            assertEquals(i, (int)result);
        }
    }

    //ReplayingDecoder解决了需要手动判断可读字节数的问题，
    //通过ReplayingDecoderByteBuf实现ByteBuf，它内部会判断可读字节数是否满足要求
    public static class ToIntegerDecoder2 extends ReplayingDecoder<Void> {
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            out.add(in.readInt());
        }
    }

    @Test
    public void test2() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeInt(i);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new ToIntegerDecoder2());
        assertTrue(channel.writeInbound(buf));
        assertTrue(channel.finish());

        for (int i = 0; i < 10; i++) {
            Integer result = channel.readInbound();
            assertEquals(i, (int)result);
        }
    }


    public static class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {
        @Override
        protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
            out.add(msg + "hello");
        }
    }

    @Test
    public void test3() {
        EmbeddedChannel channel = new EmbeddedChannel(new IntegerToStringDecoder());

        for (int i = 0; i < 10; i++) {
            assertTrue(channel.writeInbound(i));
        }


        for (int i = 0; i < 10; i++) {
            Object result = channel.readInbound();
            assertEquals(i + "hello", result);
        }
    }

    public class SafeByteToMessageDecoder extends ByteToMessageDecoder {
        private static final int MAX_FRAME_SIZE = 1024;

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            int readableCount = in.readableBytes();
            if (readableCount > MAX_FRAME_SIZE) {
                in.skipBytes(readableCount);
                //防止解码器缓冲区缓冲的数据过多耗尽内存，所以超过这个阈值就抛异常 TooLongFrameException
                throw new TooLongFrameException("");
            }
        }
    }


}

