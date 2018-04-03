package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Charles Date:2018/3/22
 * 如果要使用 SimpleDateFormat 必须每次申明一个新的对象才能避免线程安全问题
 */
@Slf4j
@ThreadSafe
public class DateFormatExample2 {


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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20180233");
        } catch (ParseException e) {
            log.error("parse Exception", e);
        }
    }
}
