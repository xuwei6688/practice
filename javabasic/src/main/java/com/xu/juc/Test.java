package com.xu.juc;

import java.util.concurrent.DelayQueue;

/**
 * @Author xuwei
 * @Date 2020/11/15
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) {
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        new Thread(()->{
            try {
                DelayedTask task = queue.take();
                System.out.println(task.getTaskName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        queue.put(new DelayedTask("任务", 3000));

    }
}
