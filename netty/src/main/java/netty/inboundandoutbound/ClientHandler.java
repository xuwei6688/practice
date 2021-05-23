package netty.inboundandoutbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author xuwei
 * @Date 2020/12/27
 * @Version V1.0
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("ClientHandler发送数据");
            ctx.writeAndFlush(1230L + i);
        }
    }
}
