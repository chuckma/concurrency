package com.mmall.concurrency.nicetyexplain.startthread;

/**
 * @author Administrator
 *
 * 不能两次 start 线程
 */
public class CantStartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
