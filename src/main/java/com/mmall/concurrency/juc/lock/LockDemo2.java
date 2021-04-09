package com.mmall.concurrency.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author mcg
 * @date 2021/4/8 14:56
 **/

public class LockDemo2 {


    public static void main(String[] args) {

        Phone1 phone = new Phone1();


//        new Thread(()->{
//            phone.sendSms();
//        },"A").start();

        new Thread(()->{
            phone.hi();
        }).start();
        new Thread(()->{
            phone.call();
        },"B").start();


    }
}


class Phone1{

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

    public void hi() {
        System.out.println("hi!");
    }
}
