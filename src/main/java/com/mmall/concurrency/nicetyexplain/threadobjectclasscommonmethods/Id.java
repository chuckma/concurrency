package com.mmall.concurrency.nicetyexplain.threadobjectclasscommonmethods;

/**
 * @Author mcg
 * @Date 2019/9/28 15:13
 *
 *
 *
 * 描述 ：ID 从 1 开始，JVM运行起来后，我们自己创建的
 * 线程的 ID 早已经不是 2
 **/

public class Id {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的ID " + Thread.currentThread().getId());
        System.out.println("子线程的ID " + thread.getId());
    }
}
