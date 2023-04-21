package netty.samples;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.nio.charset.StandardCharsets;

/**
 * @Author xuwei
 * @Date 2023/4/18
 * @Version 1.0
 **/
public class RequestSampleHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    String data = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
    System.out.println("来自客户端的数据：" +data + " 长度：" +  data.getBytes(StandardCharsets.UTF_8).length);

    ResponseSample response = new ResponseSample("OK", data, System.currentTimeMillis());

//    ctx.channel().writeAndFlush(response);
    ctx.writeAndFlush(response);
  }
}
