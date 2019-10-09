package com.mmall.concurrency.nicetyexplain;

/**
 * @Author mcg
 * @Date 2019/10/9 22:20
 * <p>
 * <p>
 * 描述：
 * 线程安全问题，演示死锁,必然死锁
 **/

public class MutiThreadError implements Runnable {

    int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        MutiThreadError r1 = new MutiThreadError();
        MutiThreadError r2 = new MutiThreadError();
        r1.flag = 1;
        r2.flag = 0;

        new Thread(r1).start();
        new Thread(r2).start();

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
                synchronized (o2) {
                    System.out.println("1");

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
                    System.out.println("0");

                }
            }
        }
    }
}
