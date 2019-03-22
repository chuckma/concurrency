package com.mmall.concurrency.example.threadLocal;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 */
public class DirtyDataInThreadLocal {


    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 2; i++) {
            MyThread myThread = new MyThread();
            executorService.execute(myThread);
        }
    }

    private static class MyThread extends Thread {
        private static boolean flag = true;

        @Override
        public void run() {
            if (flag) {
                threadLocal.set(this.getName() + ",session info.");
                flag = false;
            }
            System.out.println(this.getName() + "线程是" + threadLocal.get());
        }
    }
}
