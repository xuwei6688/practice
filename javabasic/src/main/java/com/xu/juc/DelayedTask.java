package com.xu.juc;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author xuwei
 * @Date 2020/11/15
 * @Version V1.0
 **/
public class DelayedTask implements Delayed {
    private String taskName;
    private long avaibleTime;

    public String getTaskName() {
        return taskName;
    }

    public DelayedTask(String taskName, long delayTime) {
        this.taskName = taskName;
        this.avaibleTime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(avaibleTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.avaibleTime - ((DelayedTask)o).avaibleTime);
    }
}
