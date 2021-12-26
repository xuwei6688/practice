package nio.timeserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable{
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean stop = false;

    public MultiplexerTimeServer(int port){
        try {
            selector = Selector.open();

            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            serverChannel.bind(new InetSocketAddress(port));


            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                SelectionKey selectionKey = null;


                selectionKeys.forEach(e -> System.out.println("selectedKey    " + e.interestOps() + "  " + e));

                while (keyIterator.hasNext()) {
                    selectionKey = keyIterator.next();
                    keyIterator.remove();

                    try {
                        handlerInput(selectionKey);
                    } catch (Exception e) {
                        if (selectionKey != null) {
                            //将key从Selector上移除
                            selectionKey.cancel();
                            if (selectionKey.channel() != null) {
                                selectionKey.channel().close();
                            }
                        }
                    }

//                    keyIterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                //多路复用器关闭后，注册在上面的所有 Channel 和 Pipe 都会被自动关闭
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handlerInput(SelectionKey key) throws IOException {
        System.out.println("selectionKey" + key.interestOps() + "  " + key);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);

                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    System.out.println("读取到客户端信息：" + new String(bytes));

                    String date = LocalDate.now().toString();
                    doWrite(sc, date);
                }
                else {
                    key.cancel();
                    sc.close();
                }

            }
        }

    }

    private void doWrite(SocketChannel socketChannel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            ByteBuffer writeBuffer = ByteBuffer.allocate(response.getBytes().length);
            writeBuffer.put(response.getBytes());

            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        }
    }
}
