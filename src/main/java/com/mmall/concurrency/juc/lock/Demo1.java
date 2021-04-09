package com.mmall.concurrency.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author mcg
 **/

public class Demo1 {


    public static void main(String[] args) {

        Phone5 phone5 = new Phone5();
        new Thread(() -> {
            phone5.sms();
        }, "A").start();

        new Thread(() -> {
            phone5.sms();
        }, "B").start();
    }



}


class Phone5{
    public void sms() {
        System.out.println(Thread.currentThread().getName() + "sms");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        call();
    }
    public void call() {
        System.out.println(Thread.currentThread().getName() + "call");
    }
}