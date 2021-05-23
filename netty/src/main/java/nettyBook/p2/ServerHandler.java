package nettyBook.p2;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import nettyBook.p1.RequestFuture;
import nettyBook.p1.Response;

import java.nio.charset.Charset;

/**
 * @Author xuwei
 * @Date 2021/4/18
 * @Version V1.0
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if (msg instanceof ByteBuf) {
//            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
//        }
//        ctx.channel().writeAndFlush("msg has received!");

        RequestFuture future = JSONObject.parseObject(msg.toString(), RequestFuture.class);
        long id = future.getId();
        System.out.println("请求信息为==" + msg);

        Response response = new Response();
        response.setId(id);
        response.setResult("服务器响应 ok");

        ctx.channel().writeAndFlush(JSONObject.toJSONString(response));
    }
}
