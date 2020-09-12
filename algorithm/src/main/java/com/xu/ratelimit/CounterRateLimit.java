package com.xu.ratelimit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author xuwei
 * @Date 2020/9/11 0011
 * @Version V1.0
 **/
public class CounterRateLimit implements RateLimit ,Runnable {
    private AtomicInteger counter;
    /** 阈值 **/
    private Integer limitCount;

    /**
     * 统计时间间隔
     */
    private long period;
    private TimeUnit timeUnit;

    private ScheduledExecutorService scheduledExecutorService;

    public CounterRateLimit(Integer limitCount) {
        this(limitCount, 1, TimeUnit.SECONDS);
    }

    public CounterRateLimit(Integer limitCount, long period, TimeUnit timeUnit) {
        this.limitCount = limitCount;
        this.period = period;
        this.timeUnit = timeUnit;
        this.counter = new AtomicInteger(0);
        startResetTask();
    }

    @Override
    public boolean canPass() {
        return counter.incrementAndGet() > limitCount;
    }

    private void startResetTask() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this, 0, period, timeUnit);
    }

    @Override
    public void run() {
        counter.set(0);
    }
}
