package com.mmall.concurrency.juc.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author mcg
 * @date 2021/4/9 08:54
 **/

public class BlockQueueDemo {


    public static void main(String[] args) throws InterruptedException {
//        test1ThrowException();
//        test2NotThrowException();
//        test3();
//        test4();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


    /**
     * 阻塞队列抛出异常
     */
    private static void test1ThrowException() {
        ArrayBlockingQueue<Object> q = new ArrayBlockingQueue<>(3);
        q.add(1);
        q.add(2);
        q.add(3);
        // 查看当前队首,没有的话会抛出异常
        System.out.println(q.element());

        // 下面三行都是 true
        System.out.println(q.remove());
        System.out.println(q.remove());
        System.out.println(q.remove());
        // 下面的抛出异常
        System.out.println(q.remove());
    }

    /**
     * 阻塞队列不抛出异常
     */
    private static void test2NotThrowException() {
        ArrayBlockingQueue<Object> q = new ArrayBlockingQueue<>(3);
        // 下面的返回都为 true
        System.out.println(q.offer(1));
        System.out.println(q.offer(2));
        System.out.println(q.offer(3));
        // 下面的返回为 false
        System.out.println(q.offer(4));

        // 查看当前队首
        System.out.println(q.peek());

        // 获取元素
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        // 不存在元素了 返回 null
        System.out.println(q.poll());
    }


    /**
     * 阻塞等待
     */
    private static void test3() throws InterruptedException {
        ArrayBlockingQueue<Object> q = new ArrayBlockingQueue<>(3);
        q.put(1);
        q.put(2);
        q.put(3);
        // 超过的时候,一直阻塞等待
//        q.put(4);

        System.out.println(q.take());
        System.out.println(q.take());
        System.out.println(q.take());
        // 下面这里一直等待
        System.out.println(q.take());

    }


    /**
     *
     * 超时退出
     */
    private static void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> q = new ArrayBlockingQueue<>(3);
        System.out.println(q.offer(2));
        System.out.println(q.offer(2));
        System.out.println(q.offer(2));
        // 超时退出
        q.offer(1,2, TimeUnit.SECONDS);


        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        // 获取元素 超时退出
        System.out.println(q.poll(2,TimeUnit.SECONDS));

    }
}
