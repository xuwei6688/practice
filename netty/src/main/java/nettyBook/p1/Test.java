package nettyBook.p1;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<RequestFuture> futureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            RequestFuture future = new RequestFuture("客户端请求" + i);

            futureList.add(future);

            SubThread subThread = new SubThread(future);
            subThread.start();
        }

        futureList.forEach(e->{
            System.out.println(e.get());
        });
    }
}
