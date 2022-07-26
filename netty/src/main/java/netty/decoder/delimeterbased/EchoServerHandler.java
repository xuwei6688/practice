package netty.decoder.delimeterbased;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;

        System.out.println("收到客户端发来的请求：" + message + " counter=" + ++counter);
        message += "$_";
        ctx.writeAndFlush(Unpooled.wrappedBuffer(message.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
