package netty.tes;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;

/**
 * @Author xuwei
 * @Date 2023/4/21
 * @Version 1.0
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    String str = "01234567890123456789";
    ctx.writeAndFlush(Unpooled.copiedBuffer(str.getBytes(StandardCharsets.UTF_8)));
  }
}
