package netty.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author xuwei
 * @Date 2020/12/27
 * @Version V1.0
 **/
public class ByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ByteToLongDecoder被调用");
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
