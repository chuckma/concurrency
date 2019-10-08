package com.mmall.concurrency.nicetyexplain.uncaughtexception;

/**
 * @Author mcg
 * @Date 2019/9/30 21:35
 * <p>
 * 描述：
 * 1. 不加try catch 抛出 4 个异常，都带线程名字
 * 2. 加了 try cathc ，期望捕获到第一个线程的异常，线程 234不应该
 * 运行，希望看到打印出 caught exception
 * 3. 执行时发现，根本没有 caught exception，线程 234 依然运行并
 * 抛出异常
 * <p>
 * 说明线程的异常不能用传统的方式捕获
 **/

public class CantCatchDirectly implements Runnable {


    public static void main(String[] args) {

        try {
            new Thread(new CantCatchDirectly(),"my-thread-1").start();
            new Thread(new CantCatchDirectly(),"my-thread-2").start();
            new Thread(new CantCatchDirectly(),"my-thread-3").start();
            new Thread(new CantCatchDirectly(),"my-thread-4").start();

        } catch (RuntimeException e) {
            System.out.println("Caught Exception");
        }
    }
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
