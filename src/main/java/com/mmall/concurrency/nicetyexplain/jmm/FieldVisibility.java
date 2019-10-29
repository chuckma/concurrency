package com.mmall.concurrency.nicetyexplain.jmm;

/**
 * @Author mcg
 * @Date 2019/10/29 20:52
 * @Desc 演示 可见性带来的问题
 *
 * 可见性发生的根本原因
 *      在主内存和CPU 之间有多机缓存，这个多机缓存之间是不共享的， 读取到的数据可能过期
 *      如果所有的核心都只有一个缓存，那么就不存才内存可见性的问题了，
 *      但是通常情况下，每个核心都会将自己需要的数据读到独占缓存中，数据修改后也是写入到缓存
 *      中，然后等待刷入主存中，所以或导致有些核心读取的值是一个过期的值
 **/

public class FieldVisibility {

     volatile int a = 1;
     volatile int b = 2;

    private void change() {
        a = 3;
        b = a;
    }


    private void print() {
        System.out.println("b=" + b + ";a=" + a);
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility test = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }

    }
}
