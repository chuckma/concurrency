package com.mmall.concurrency.nicetyexplain.uncaughtexception;

/**
 * @Author mcg
 * @Date 2019/9/30 21:30
 *
 *
 * 描述：单线程 抛出，有异常堆栈
 *  多线程，子线程发生异常，会有什么不同
 *
 *  结论：主线程可以轻松发现异常，子线程却不行
 *
 *  子线程异常无法用传统的方法捕获
 *
 **/

public class ExceptionInChildThread implements Runnable{


    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
