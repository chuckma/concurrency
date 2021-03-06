package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Charles Date:2018/3/22
 * SimpleDateFormat 不是一个线程安全的对象
 */
@Slf4j
@NotThreadSafe
public class DateFormatExample1 {


    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool(); // 线程池
        final Semaphore semaphore = new Semaphore(threadTotal); //信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); // 计数器闭锁
        for (int i = 0;i<clientTotal;i++) {
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void add(){
        try {
            simpleDateFormat.parse("20180405");
        } catch (ParseException e) {
            log.error("parse Exception", e);
        }
    }
}
