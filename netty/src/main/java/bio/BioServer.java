package bio;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author xuwei
 * @Date 2020/12/6
 * @Version V1.0
 **/
public class BioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        ExecutorService executor = Executors.newCachedThreadPool();

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("接收到客户端连接");

            executor.execute(()->{
                handler(socket);
            });
        }
    }

    private static void handler(Socket socket) {

        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            System.out.println("线程id:" + Thread.currentThread().getId());
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println("读取到数据，线程id:" + Thread.currentThread().getId());
                    System.out.println(new String(bytes, 0 , read));
                }else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("关闭客户端连接");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
