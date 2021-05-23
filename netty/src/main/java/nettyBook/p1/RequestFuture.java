package nettyBook.p1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求客户端类，包含有请求参数，响应结果。
 * 最主要的方法是 get 和 received
 * <p>get：获取响应结果，如果还没有响应会一直阻塞，直到有响应</p>
 * <p>received： 模拟接收响应，接收到响应后会将结果存到 result 中，然后唤醒在 get 方法阻塞的线程。接收
 *              到请求后如何知道是对应哪个 RequestFuture 发出的呢？所以维护了一个 futureMap，
 *              维护请求 id 和 RequestFuture 的对应关系。</p>
 */
public class RequestFuture {
    private static final Map<Long, RequestFuture> futureMap = new ConcurrentHashMap<>();
    private static final AtomicInteger idGenerator = new AtomicInteger();
    private long id;
    private Object request;
    private Object result;
    private final static long TIMEOUT = 5000;

    public RequestFuture(Object request) {
        this.id = idGenerator.incrementAndGet();
        this.request = request;
        futureMap.put(id, this);
    }

    public Object get() {
        synchronized (this) {
            while (result == null) {
                try {
                    wait(TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void received(Response response) {
        if (response == null) {
            return;
        }

        RequestFuture future = futureMap.get(response.getId());
        if (future == null) {
            return;
        }
        future.setResult(response.getResult());

        //通知主线程
        synchronized (future) {
            future.notify();
        }
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
