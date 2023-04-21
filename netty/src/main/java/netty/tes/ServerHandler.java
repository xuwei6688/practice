package netty.tes;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Author xuwei
 * @Date 2023/4/21
 * @Version 1.0
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    String message = ((ByteBuf) msg).toString((CharsetUtil.UTF_8));
    System.out.println("从客户端接收到的消息：" + message + " 长度：" + message.length());
  }
}
