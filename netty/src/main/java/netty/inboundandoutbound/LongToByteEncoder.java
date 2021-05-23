package netty.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author xuwei
 * @Date 2020/12/27
 * @Version V1.0
 **/
public class LongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("LongToByteEncoder 被调用，msg=" + msg);

        //将Long类型编码后写入ByteBuf中
        out.writeLong(msg);
    }
}
