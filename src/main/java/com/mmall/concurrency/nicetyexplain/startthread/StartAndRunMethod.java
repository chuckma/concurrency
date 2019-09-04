package com.mmall.concurrency.nicetyexplain.startthread;

/**
 * @author mcg
 *
 * start and run  两种启动线程的方式
 *
 * start 的含义
 * 1 启动新线程（告诉jvm 合适的时机启动线程）
 * 2 准备工作
 *
 */
public class StartAndRunMethod {


    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        r.run();

        new Thread(r).start();
    }
}
