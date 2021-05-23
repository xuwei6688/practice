package netty.tcp2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Author xuwei
 * @Date 2020/12/12
 * @Version V1.0
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //当通道就绪时就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            String msg = "hello-" + i;

            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setContent(msg.getBytes());
            messageProtocol.setLength(msg.getBytes().length);

            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
