package netty.tes;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author xuwei
 * @Date 2023/4/21
 * @Version 1.0
 **/
public class Client {
  public static void main(String[] args) throws InterruptedException {
    NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    Bootstrap bootstrap = new Bootstrap()
        .group(eventLoopGroup)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<Channel>() {
          @Override
          protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new ClientHandler());
          }
        });

    ChannelFuture channelFuture = bootstrap.connect("localhost", 9999).sync();
    channelFuture.channel().closeFuture().sync();
  }
}
