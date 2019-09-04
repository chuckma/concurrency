package com.mmall.concurrency.nicetyexplain;

/**
 * @author Administrator
 *
 *
 * Runnable 实现的 线程
 */
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable 实现的 线程");
    }


    public static void main(String[] args) {
        new Thread(new RunnableStyle()).start();
    }
}
