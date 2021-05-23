package nettyBook.p2;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import nettyBook.p1.RequestFuture;
import nettyBook.p1.Response;

import java.util.concurrent.ExecutionException;

public class Client {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        NioEventLoopGroup group = new NioEventLoopGroup();
        //新建一个Promise对象
        Promise<Response> promise = new DefaultPromise<>(group.next());
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.setPromise(promise);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0, 4, 0, 4));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(clientHandler);
                ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                ch.pipeline().addLast(new StringEncoder());
            }
        });

        //连接服务器
        ChannelFuture future = bootstrap.connect("127.0.0.1", 8081).sync();

        RequestFuture requestFuture = new RequestFuture("hello world!");
        String requestStr = JSONObject.toJSONString(requestFuture);
        future.channel().writeAndFlush(requestStr);
        //同步阻塞等待响应结果
        Response response = promise.get();
        System.out.println(JSONObject.toJSONString(response));
    }
}
