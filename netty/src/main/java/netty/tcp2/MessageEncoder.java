package netty.tcp2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author xuwei
 * @Date 2020/12/27
 * @Version V1.0
 **/
public class MessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MessageEncoder 被调用");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
