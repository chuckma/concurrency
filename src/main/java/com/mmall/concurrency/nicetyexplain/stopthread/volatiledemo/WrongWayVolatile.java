package com.mmall.concurrency.nicetyexplain.stopthread.volatiledemo;

/**
 * @author Administrator
 *
 *
 * 演示用 volatile 的局限
 * part1 看似可行的方式
 */
public class WrongWayVolatile implements Runnable{

    private volatile boolean canceld = false;


    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceld) {
                if (num % 100 == 0) {
                    System.out.println(num + "是 100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r = new WrongWayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);
        r.canceld = true;
    }
}
