package com.mmall.concurrency.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author mcg
 * @date 2021/4/9 16:01
 * <p>
 * 验证 volatile 的不保证原子性
 **/

public class VolatileDemo2 {



    //
    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {

        // 理论上num = 20000
        for (int i = 0; i < 20; i++) {
           new Thread(() -> {
               // 每个线程执行 ++ 执行 1000 次
               for (int i1 = 0; i1 < 1000; i1++) {
                   add();
               }
           }, String.valueOf(i)).start();
        }
        System.out.println(num);
    }

    private static void add() {
        num++;
    }
}
