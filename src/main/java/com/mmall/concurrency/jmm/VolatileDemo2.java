package com.mmall.concurrency.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mcg
 * @date 2021/4/9 16:01
 * <p>
 * 验证 volatile 的不保证原子性
 **/

public class VolatileDemo2 {



    //
    private  volatile static AtomicInteger num = new AtomicInteger();
    private volatile static int num2 = 0;

    public static void main(String[] args) throws InterruptedException {

        // 理论上num = 20000
        for (int i = 1; i <= 20; i++) {
           new Thread(() -> {
               // 每个线程执行 ++ 执行 1000 次
               for (int i1 = 0; i1 < 1000; i1++) {
                   add();
               }
           }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) { // main gc
            Thread.yield();
        }
        System.out.println(num);

//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                for (int i1 = 0; i1 < 1000; i1++) {
//                    add2();
//                }
//            }, String.valueOf(i)).start();
//        }
//        while (Thread.activeCount() > 2) { // main gc
//            Thread.yield();
//        }
//        System.out.println(num2);
    }

    private static void add() {
        num.getAndIncrement();
//        num.incrementAndGet();
    }

    private static void add2() {
        num2++;
    }
}
