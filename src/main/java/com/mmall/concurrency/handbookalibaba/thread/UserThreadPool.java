package com.mmall.concurrency.handbookalibaba.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author mcg
 * @Date 2019/4/14 22:00
 **/

public class UserThreadPool {

    public static void main(String[] args) {
        // 缓存队列设置 2 为了加快触发 reject
        BlockingQueue queue = new LinkedBlockingDeque(2);

        UserThreadFactory f1 = new UserThreadFactory("第一机房");
        UserThreadFactory f2 = new UserThreadFactory("第二机房");


        UserRejectHandler rejectHandler = new UserRejectHandler();

        // 核心线程1，最大线程为 2
        ThreadPoolExecutor threadPoolExecutorFirst =
                new ThreadPoolExecutor(1, 2,
                        60, TimeUnit.SECONDS, queue, f1, rejectHandler);

        ThreadPoolExecutor threadPoolExecutorSecond =
                new ThreadPoolExecutor(1, 2,
                        60, TimeUnit.SECONDS, queue, f2, rejectHandler);


        // 创建 400 个任务线程

        Runnable task = new Task();
        for (int i = 0; i < 200; i++) {
            threadPoolExecutorFirst.execute(task);
            threadPoolExecutorSecond.execute(task);
        }
    }
}
