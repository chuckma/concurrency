package com.mmall.concurrency.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author mcg
 * @date 2021/4/9 16:01
 * <p>
 * 验证 volatile 的可见性
 **/

public class VolatileDemo {

    /**
     * 没有 volatile 修饰的时候 线程 s 是一直循环执行的
     * 因为线程 s 的工作内存中拿到的 num 是 0 ,但是 main 线程
     * 已经将 num 修改为 1 并刷回了主存,此时对于 s 线程来说,它是不知道的,
     * s 线程的工作内存中的 num 还是 0 所以 while 循环会一直执行.
     * 当用 volatile 修饰,num 对于各个线程都是可见的,所以 s 线程很快就退出了循环
     */
//    private static int num = 0;
    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {

       new Thread(() -> {
           while (num == 0) {

           }
       }, "s").start();

       // 保证让 s 线程先 copy 到 num 的值到 工作内存
        TimeUnit.SECONDS.sleep(2);
        num = 1;
        System.out.println(num);

    }
}
