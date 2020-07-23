package com.mmall.concurrency.nicetyexplain.deadlock;


/**
 * @author Administrator
 */
public class MustDeadLockByMe implements Runnable {

    int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();


    public static void main(String[] args) {
        MustDeadLockByMe r1 = new MustDeadLockByMe();
        MustDeadLockByMe r2 = new MustDeadLockByMe();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1= new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

    }
    @Override
    public void run() {
        System.out.println("flag" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o2) {
                    System.out.println("线程1成功拿到两把锁");
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

                synchronized (o1) {
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }
}
