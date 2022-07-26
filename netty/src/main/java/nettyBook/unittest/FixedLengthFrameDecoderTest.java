package nettyBook.unittest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import static org.junit.Assert.*;
import org.junit.Test;

public class FixedLengthFrameDecoderTest {
    @Test
    public void testFramesDecoded() {
        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0; i < 9; i++) {
            byteBuf.writeByte(i);
        }

        ByteBuf buf = byteBuf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        //retain引用增加1
        assertTrue(channel.writeInbound(buf.retain()));
        assertTrue(channel.finish());//不允许写入了，

        ByteBuf result1 = (ByteBuf)channel.readInbound();
        assertEquals(byteBuf.readSlice(3), result1);
        result1.release();

        ByteBuf result2 = (ByteBuf)channel.readInbound();
        assertEquals(byteBuf.readSlice(3), result2);
        result2.release();

        ByteBuf result3 = (ByteBuf)channel.readInbound();
        assertEquals(byteBuf.readSlice(3), result3);
        result3.release();

        assertNull(channel.readInbound());
        buf.release();

    }
}
