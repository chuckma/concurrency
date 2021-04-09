package com.mmall.concurrency.juc.sq;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author mcg
 * @date 2021/4/9 09:29
 *
 * 同步队列,一次只能添加一个,只有等存进去的元素拿出来,才能放入下一个,类似排队.
 * 你需要一个个来
 **/

public class SynchronousQueueDemo {


    public static void main(String[] args) throws InterruptedException {
            test1();
    }


    /**
     * 抛出异常
     */
    private static void test1() throws InterruptedException {
        SynchronousQueue<Object> queue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"put 1");
                queue.put(1);
                System.out.println(Thread.currentThread().getName()+"put 2");
                queue.put(2);
                System.out.println(Thread.currentThread().getName() + "put 3");
                queue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"->"+queue.take());
                TimeUnit.SECONDS.sleep(2);

                System.out.println(Thread.currentThread().getName()+"->"+queue.take());
                TimeUnit.SECONDS.sleep(2);

                System.out.println(Thread.currentThread().getName()+"->"+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();
    }
}
