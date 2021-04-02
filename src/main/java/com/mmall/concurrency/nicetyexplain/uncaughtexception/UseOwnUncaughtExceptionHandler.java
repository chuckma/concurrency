package com.mmall.concurrency.nicetyexplain.uncaughtexception;

/**
 * @author mcg
 * @date 2021/4/2 09:39
 **/

public class UseOwnUncaughtExceptionHandler implements Runnable {


    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new CustomUncaughtExceptionHandler("蒲红旗 1"));
        new Thread(new CantCatchDirectly(), "my-thread-1").start();
        new Thread(new CantCatchDirectly(), "my-thread-2").start();
        new Thread(new CantCatchDirectly(), "my-thread-3").start();
        new Thread(new CantCatchDirectly(), "my-thread-4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
