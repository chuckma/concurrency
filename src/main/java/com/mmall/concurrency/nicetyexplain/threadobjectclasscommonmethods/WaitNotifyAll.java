package com.mmall.concurrency.nicetyexplain.threadobjectclasscommonmethods;

/**
 * @Author mcg
 * @Date 2019/9/10 22:22
 *
 *
 *  描述：3 个线程，线程1和线程2首先被阻塞，线程3唤醒他们
 *  比较 notify 和 notifyall 的区别
 *  start 先执行不代表先启动
 **/

public class WaitNotifyAll  implements Runnable{

    private static final Object resouceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new  Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resouceA){
//                    resouceA.notifyAll();
                    // 只会选择一个线程去唤醒
                    resouceA.notify();
                    System.out.println("ThreadC notified");
                }

            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(200);
        threadC.start();
    }
    @Override
    public void run() {
        synchronized (resouceA) {
            System.out.println(Thread.currentThread().getName() + "got rescoureA lock.");

            try {
                System.out.println(Thread.currentThread().getName() + "waits to start.");
                resouceA.wait();
                System.out.println(Thread.currentThread().getName() + "'s waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
