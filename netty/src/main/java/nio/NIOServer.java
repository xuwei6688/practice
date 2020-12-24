package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author xuwei
 * @Date 2020/12/7
 * @Version V1.0
 **/
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个Selector对象
        Selector selector = Selector.open();

        //绑定一个端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //将serverSocketChannel注册到Selector，关心OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            //Selector在select这里阻塞，直到有事件发生
            if (selector.select(2000) == 0) {
                System.out.println("服务器等了2秒，无连接");
                continue;
            }

            //获取有事件发生的selectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            //遍历有事件发生的selectionKey集合
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                //如果是连接事件，新建获取一个连接
                if (key.isAcceptable()) {
                    //生成SocketChannel，这里accept不会阻塞，因为已经明确有连接了
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成了一个socketChannel " + socketChannel.hashCode());

                    socketChannel.configureBlocking(false);

                    //将socketChannel注册到selector，关注OP_READ事件
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                //如果是可读事件发生，读取数据
                }

                if (key.isReadable()) {
                    //通过key，获取关联的channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from客户端 " + new String(buffer.array()));
                }

                //手动从集合中移除当前的selectionKey防止重复操作
                keyIterator.remove();
            }
        }
    }
}
