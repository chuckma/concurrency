package com.mmall.concurrency.nicetyexplain.threadobjectclasscommonmethods;

/**
 * @Author mcg
 * @Date 2019/9/11 21:11
 * <p>
 * 描述：两个线程交替打印0-100的奇偶数，用synchronized 关键字实现
 **/

public class WaitNotifyPrintOddEvenSyn {
    private static int count;
    private static final Object lock = new Object();

    // 新建 2 个线程

    // 1个只处理偶数，另外一个只处理奇数（用位运算）

    // 用 synchronized 来通信
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
//                            count++;
                        }
                    }
                }
            }
        },"偶数").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        },"奇数").start();

    }
}
