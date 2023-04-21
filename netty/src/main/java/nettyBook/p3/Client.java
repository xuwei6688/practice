package nettyBook.p3;

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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 不使用 Netty 的 Promise，而是使用 RequestFuture.get 完成阻塞等待异步响应。
 * <p>p2 中，ClientHandler收到服务端请求后，在 channelRead 方法中调用 Promise 的 setSuccess 通知阻塞的 get</p>
 * <p>p3 中，ClientHandler收到服务端请求后，在 channelRead 方法中调用 RequestFuture 的 received 通知阻塞的 get</p>
 */
public class Client {
    private static ChannelFuture future;


    static {
       Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0, 4, 0, 4));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new ClientHandler());
                        ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        try {
            future = bootstrap.connect("127.0.0.1", 8081).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public RequestFuture sendMsg(String msg) {
        RequestFuture request = new RequestFuture(msg);

        future.channel().writeAndFlush(JSONObject.toJSONString(request));
        return request;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < 10; i++) {
            RequestFuture future = new Client().sendMsg("hello" + i);
            System.out.println(future.get() + String.valueOf(future.getId()));
        }
    }
}
