package com.mmall.concurrency.nicetyexplain.threadobjectclasscommonmethods;

/**
 * @author Administrator
 *
 *
 * 看 join期间线程的状态‘
 * 通过 Thread.getState() 或者 debugger 看线程 join 前后状态的对比
 */
public class JoinThreadState {


    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();


       Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(mainThread.getState());
                    System.out.println("Thread-0 运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("等待子线程运行 完毕");
        thread.join();
        System.out.println("子线程运行完毕");
    }
}
