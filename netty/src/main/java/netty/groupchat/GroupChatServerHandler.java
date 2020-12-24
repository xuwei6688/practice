package netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;



/**
 * @Author xuwei
 * @Date 2020/12/18
 * @Version V1.0
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //handlerAdded 表示连接建立，一旦建立，第一个被执行
    //将当前channel 加入到 channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        /**
         * 将该客户端加入聊天的消息推送给其它在线的客户端
         * 该方法会将 ChannelGroup 中所有的 channel 遍历，并发送消息，不需要我们自己遍历
         */
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 加入聊天\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 离开了\n");
        //触发handlerRemoved后，Netty自动把当前channel从group中删除
        System.out.println("channelGroup size" + channelGroup.size());
    }

    //表示Channel处于活动状态，提示XX上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线了~");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离线了~");
    }

    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            if (channel != ch) {
                //不是当前channel，转发消息
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + " 发送了消息" + s + "\n");
            }else{
                //回显自己发送的消息
                ch.writeAndFlush("[自己]发送了消息" + s + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
