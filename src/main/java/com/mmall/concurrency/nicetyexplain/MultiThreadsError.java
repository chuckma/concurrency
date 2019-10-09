package com.mmall.concurrency.nicetyexplain;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author mcg
 * @Date 2019/10/8 20:56
 * <p>
 * <p>
 * 描述：
 * 第一种：运行结果出错
 * <p>
 * 演示计数不准确（减少），找出具体出错的位置。
 **/

public class MultiThreadsError implements Runnable {
    int index = 0;

    final boolean[] marked = new boolean[10000000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);


    static MultiThreadsError instance = new MultiThreadsError();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是" + instance.index);
        System.out.println("真正运行的次数是" + realIndex.get());
        System.out.println("错误的次数" + wrongCount.get());
    }

    @Override
    public void run() {

        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                // 监测所等待的 2 个线程都到齐了（执行了 await() ）就会放行
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生了错误" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;

            }
        }
    }
}
