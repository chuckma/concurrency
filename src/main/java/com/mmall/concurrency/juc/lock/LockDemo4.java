package com.mmall.concurrency.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author mcg
 *
 * 一个对象,两个同步方法
 **/

public class LockDemo4 {


    public static void main(String[] args) {


        /**
         * 在这个demo 代码里, Phone4 里的同步方法被 static 修饰了
         * 此处锁的对象的是 class,也就是 Phone4.class,那么下面的 sendSms 是先执行的
         * 两个对象的Class类模板只有一个
         * */
        Phone4 phone = new Phone4();

        new Thread(()->{
            phone.sendSms();
        },"A").start();


        new Thread(()->{
            phone.call();
        },"B").start();

    }
}


// Phone4 只有唯一的 class
class Phone4{

    // synchronized 锁的是方法的调用者
    // 两个方法用的是同一个锁,谁先拿到谁执行

    /**
     * 对于 static ,在类一加载的时候就有了,此处锁的是 class
     */
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发消息");
    }

    /**
     * 对于 static ,在类一加载的时候就有了,此处锁的是 class
     */
    public static synchronized void call() {
        System.out.println("打电话");
    }
}
