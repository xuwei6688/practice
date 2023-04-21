package netty.tes;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

/**
 * @Author xuwei
 * @Date 2023/4/21
 * @Version 1.0
 **/
public class Server {

  public static void main(String[] args) {
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
       ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
          .channel(NioServerSocketChannel.class)
          .childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
              ch.pipeline().addLast(new FixedLengthFrameDecoder(10));
              ch.pipeline().addLast(new ServerHandler());
            }
          });

       ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
       channelFuture.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }
}
