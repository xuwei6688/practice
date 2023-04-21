package netty.samples;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author xuwei
 * @Date 2023/4/18
 * @Version 1.0
 **/
public class ResponseSampleEncoder extends MessageToByteEncoder<ResponseSample> {

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, ResponseSample msg, ByteBuf out) throws Exception {

    if (msg != null) {
      out.writeBytes(msg.getCode().getBytes());
      out.writeBytes(msg.getData().getBytes());
      out.writeLong(msg.getTimestamp());
    }
  }
}
