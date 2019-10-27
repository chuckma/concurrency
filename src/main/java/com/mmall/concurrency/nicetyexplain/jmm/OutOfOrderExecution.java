package com.mmall.concurrency.nicetyexplain.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Author mcg
 * @Date 2019/10/27 14:56
 * <p>
 * <p>
 * 演示重排序
 * 重排序不一定每次都出现，需要设置直到达到一定条件才停止，测试小概率事件
 *
 * 什么是重排序？
 * 在线程 1 内部的两行代码的实际执行顺序和代码在Java文件中的顺序不一致，代码指令
 * 并不是严格按照代码顺序执行的，他们的顺序被改变了，这就是重排序，这里被颠倒的是y=a 和
 * b=1 这两行语句
 **/

public class OutOfOrderExecution {


    private static int x = 0, y = 0;
    private static int a = 0, b = 0;


    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch latch = new CountDownLatch(1);
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });

            two.start();
            one.start();
            // 收到信号，两个线程同时执行
            latch.countDown();
            one.join();
            two.join();

            String result = "第"+i+"次（"+x+","+y+")";
            if (x == 0 && y == 0 ) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }

        }
    }
}
