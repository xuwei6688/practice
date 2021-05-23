package nettyBook.p3;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Promise;
import nettyBook.p1.RequestFuture;
import nettyBook.p1.Response;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response = JSONObject.parseObject(msg.toString(), Response.class);
        RequestFuture.received(response);
    }

}
