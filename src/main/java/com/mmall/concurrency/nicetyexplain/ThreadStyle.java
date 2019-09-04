package com.mmall.concurrency.nicetyexplain;

/**
 * @author Administrator
 *
 * Thread 实现 线程
 */
public class ThreadStyle extends Thread {

    @Override
    public void run() {
        System.out.println("Thread 实现 线程");
    }


    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
