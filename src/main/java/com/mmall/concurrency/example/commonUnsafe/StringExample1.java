package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Charles Date:2018/3/22
 *
 * StringBuilder 线程不安全, 在一个方法内部定义局部变量进行使用的时候,属于堆栈封闭, 这个时候只有单个线程才能操作这个对象
 * 这个时候就不涉及线程安全的问题. eg. 在方法内部定义一个StringBuilder 对象来操作 字符串
 */
@Slf4j
@NotThreadSafe
public class StringExample1 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool(); // 线程池
        final Semaphore semaphore = new Semaphore(threadTotal); //信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); // 计数器闭锁
        for (int i = 0;i<clientTotal;i++) {
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", stringBuilder.length());
    }

    private static void update(){
        stringBuilder.append("1");
    }
}
