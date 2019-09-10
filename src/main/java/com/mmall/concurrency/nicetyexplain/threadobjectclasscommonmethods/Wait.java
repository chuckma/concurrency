package com.mmall.concurrency.nicetyexplain.threadobjectclasscommonmethods;

/**
 * @Author mcg
 * @Date 2019/9/10 22:09
 * <p>
 * <p>
 * 展示 wait  和  notify 的基本用法
 * 1. 研究代码的执行顺序
 * 2. 证明 wait 是释放锁的。
 **/

public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() + "开始执行");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了 notify");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }

    // 运行结果

    /*
    *
    * 线程Thread-0开始执行
        线程Thread-1调用了 notify
        线程Thread-0获取到了锁
    * */

}
