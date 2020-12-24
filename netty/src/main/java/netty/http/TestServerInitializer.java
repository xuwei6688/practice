package netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author xuwei
 * @Date 2020/12/13
 * @Version V1.0
 **/
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道加入处理器
        ChannelPipeline pipeline = socketChannel.pipeline();

        //加入一个netty提供的HttpServerCodec codec =>[coder - decoder]
        //HttpServerCodec 说明
        //1.HttpServerCodec 是netty 提供的处理http的编码解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        //2.增加一个自定义的handler
        pipeline.addLast("MyTestHttpServerHandler", new TestHttpServerHandler());
    }
}
