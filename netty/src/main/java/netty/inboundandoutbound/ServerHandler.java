package netty.inboundandoutbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author xuwei
 * @Date 2020/12/27
 * @Version V1.0
 **/
public class ServerHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("收到客户端发来的消息：" + msg);
    }
}
