package nettyBook.p1;

import java.util.concurrent.TimeUnit;

public class SubThread extends Thread{
    private RequestFuture future;

    public SubThread(RequestFuture future) {
        this.future = future;
    }

    @Override
    public void run() {
        Response response = new Response();
        response.setId(future.getId());
        response.setResult("server response " + Thread.currentThread().getId());

        try {
            //这里休眠1秒，模拟服务端处理、响应消耗时间
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RequestFuture.received(response);
    }
}
