package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureExample {

    // 定义一个Callable的任务 MyCallable , 这里返回一个String 类型
    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            // 假如 在这个callable 做一些事情需要比较长时间 , 让线程 sleep 5s
            log.info("do something in callable");
            Thread.sleep(5000);
            // 任务完成了 返回 Done
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {

        // 使用Future和 Callable 的时候一般是通过线程池类调用的 . 索引在这里 申明一个线程池

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 让线程池直接提交该任务 , 返回任务结果
        // 这样就相当于用 Future 接收了另外一个线程任务计算的结果
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        // 获取之前的任务返回了什么结果
        String result = future.get();
        log.info("result：{}", result);
    }
}
