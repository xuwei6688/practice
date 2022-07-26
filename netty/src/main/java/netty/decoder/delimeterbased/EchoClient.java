package netty.decoder.delimeterbased;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.StandardCharsets;

public class EchoClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf byteBuf = Unpooled.copiedBuffer("$_".getBytes(StandardCharsets.UTF_8));

                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
            System.out.println("hello");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        }finally {
            group.shutdownGracefully();
        }

    }
}
