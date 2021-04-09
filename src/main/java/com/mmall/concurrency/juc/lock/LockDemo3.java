package com.mmall.concurrency.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author mcg
 *
 * 两个对象,两个同步方法
 **/

public class LockDemo3 {


    public static void main(String[] args) {

        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();

        new Thread(()->{
            phone.sendSms();
        },"A").start();


        new Thread(()->{
            phone2.call();
        },"B").start();

    }
}


class Phone3{

    // synchronized 锁的是方法的调用者
    // 两个方法用的是同一个锁,谁先拿到谁执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发消息");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}
