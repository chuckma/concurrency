package com.mmall.concurrency.nicetyexplain.sixstates;

/**
 * @Author mcg
 * @Date 2019/9/6 18:51
 * <p>
 * <p>
 * 描述: 展示线程的 new，runnable，terminated 状态
 * 即使是正在运行，也是 Runable 状态，而不是 Running
 **/

public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());

        // 打印出 new 的状态
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印出 Runnable
        System.out.println(thread.getState());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 打印出 Terminated
        System.out.println(thread.getState());

    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
