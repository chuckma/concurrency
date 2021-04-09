package com.mmall.concurrency.handbookalibaba.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author mcg
 * @Date 2019/4/14 21:45
 **/

public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;

    private final AtomicInteger nexId = new AtomicInteger(1);

    // 定义线程组名称
    UserThreadFactory(String whatFeatureOfGroup) {
        namePrefix = "UserThreadFactory's " + whatFeatureOfGroup + " -Worker- ";
    }


    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nexId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0);
//        Thread thread = new Thread(name);
        System.out.println(thread.getName());
        return thread;
    }

}

class Task implements Runnable {
    private final AtomicLong count = new AtomicLong(0L);

    @Override
    public void run() {
        System.out.println("running_" + count.getAndIncrement());
    }
}
