package netty.tcp2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author xuwei
 * @Date 2020/12/27
 * @Version V1.0
 **/
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MessageDecoder 被调用");
        int length = in.readInt();

        byte[] content = new byte[length];
        in.readBytes(content);

        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLength(length);
        messageProtocol.setContent(content);
        out.add(messageProtocol);
    }
}
