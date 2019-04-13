package com.mmall.concurrency.example.bookart.jucutils;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author mcg
 * @Date 2019/4/13 09:01
 **/

public class CyclicBarrierTest2 {


    /**
     * 因为CyclicBarrier设置了拦截线程的数量是2，所以必须等代码中的第一个线程和线程A
     * 都执行完之后，才会继续执行主线程，然后输出2
     */

    static CyclicBarrier c = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                }
                System.out.println(1);
            }
        }).start();
        try {
            c.await();
        } catch (Exception e) {
        }
        System.out.println(2);
    }

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
