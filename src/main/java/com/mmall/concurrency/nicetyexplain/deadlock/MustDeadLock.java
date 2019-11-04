package com.mmall.concurrency.nicetyexplain.deadlock;

/**
 * 必定发生死锁
 */
public class MustDeadLock implements Runnable{

    int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();


    public static void main(String[] args) {
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

    }
    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 对于第 1 个线程而言，去尝试获取第 2 把锁
                synchronized (o2) {
                    System.out.println("线程 1 成功拿到两把锁");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 对于第 2 个线程而言，去尝试获取第 1 把锁
                synchronized (o1) {
                    System.out.println("线程 2 成功拿到两把锁");
                }
            }
        }
    }
}
