package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author xuwei
 * @Date 2020/12/7
 * @Version V1.0
 **/
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        //因为是非阻塞的，所以这里没连接上线程还可以做其它工作
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要事件，客户端不会阻塞，可以做其它工作..");
            }

            String str = "hello，尚硅谷~";

            //地城数组大小取决于str字节长度，避免了手动指定buffer大小
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            socketChannel.write(buffer);

            System.in.read();
        }
    }
}
