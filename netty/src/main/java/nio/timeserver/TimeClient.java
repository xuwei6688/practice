package nio.timeserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class TimeClient {
    public static void main(String[] args) throws IOException {
        new Thread(new TimeClientHandler("127.0.0.1", 9999)).start();
    }
}
