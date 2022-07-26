package nettyBook.unittest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbsIntegerEncoderTest {
    @Test
    public void test1() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 10; i++) {
            buf.writeInt(i * -1);
        }

        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        assertTrue(channel.writeOutbound(buf.retain()));
        assertTrue(channel.finish());

        for (int i = 0; i < 10; i++) {
            assertEquals(i, (int)channel.readOutbound());
        }

        assertNull(channel.readOutbound());
    }
}
