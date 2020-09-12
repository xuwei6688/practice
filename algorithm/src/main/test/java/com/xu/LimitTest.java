package com.xu;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author xuwei
 * @Date 2020/9/11 0011
 * @Version V1.0
 **/
public class LimitTest {
    @Test
    public void test1() throws InterruptedException {
        RateLimiter r = RateLimiter.create(5);
        TimeUnit.SECONDS.sleep(2);
        while (true) {
            System.out.println("get 1 tokens: " + r.acquire() + "s");
        }
    }
}
