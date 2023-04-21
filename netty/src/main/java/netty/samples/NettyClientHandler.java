package netty.samples;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.nio.charset.StandardCharsets;

/**
 * @Author xuwei
 * @Date 2020/12/12
 * @Version V1.0
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //当通道就绪时就会触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client " + ctx);
        String req = "012345678901234567890123456789";
        System.out.println("客户端请求报文长度=" + req.getBytes(StandardCharsets.UTF_8).length);

        ctx.writeAndFlush(Unpooled.copiedBuffer(req, CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final String response = ((ByteBuf) msg).toString(StandardCharsets.UTF_8);
        System.out.println("来自服务端的响应：" + response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
