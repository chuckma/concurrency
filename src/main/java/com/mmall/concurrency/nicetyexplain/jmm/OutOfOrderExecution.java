package com.mmall.concurrency.nicetyexplain.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Author mcg
 * @Date 2019/10/27 14:56
 * <p>
 * <p>
 * 演示重排序
 * 重排序不一定每次都出现，需要设置直到达到一定条件才停止，测试小概率事件
 *
 * 什么是重排序？
 * 在线程 1 内部的两行代码的实际执行顺序和代码在Java文件中的顺序不一致，代码指令
 * 并不是严格按照代码顺序执行的，他们的顺序被改变了，这就是重排序，这里被颠倒的是y=a 和
 * b=1 这两行语句
 *
 *
 * 重排序有哪 3 种可能性
 * 1. 编译器优化
 *    编译器（包括 JVM ，JIT编译器）出于优化的目的（例如当前有了数据 a ，那么如果把对a 的操作放到一起效率会更高，
 *    避免了读取b 后又返回来重新读取a 时间开销），在编译的过程中会进行一定程度的重排，导致生成的机器指令和之前的字节码
 *    顺序不一致。
 *
 * 2. 指令重排序
 *    CPU的优化行为，和编译器优化很类似，是通过乱序执行的技术，来提高执行的效率。所以就算编译器不发生重排，CPU也可能对
 *    指令进行重排，所以我们开发中，一定要考虑重排序带来的后果。
 *
 * 3. 内存的"重排序"
 *    内存系统内不存在重排序， 但是内存会带来看上去和重排序一样的效果，所以这里的重排序打了引号，由于内存有缓存的存在，在
 *    JMM 里表现为主存和本地内存，由于主存和本地内存的不一致，会使得程序表现出乱序的行为。
 *    在当前这个类中的例子，假设没有编译器重排和指令重排，但是如果发生了内存缓存不一致，也可能导致同样的情况，线程1修改了a
 *    的值，但是修改后并没有写会主存，所以线程2是看不懂刚才线程1对a 的修改的，所以线程2看到 a 还是等于 0 。 同理，线程 2
 *    对于 b 的赋值操作也可能由于没有及时的写会主存，导致线程1看不到刚才线程2 的修改。
 **/

public class OutOfOrderExecution {


    private static int x = 0, y = 0;
    private static int a = 0, b = 0;


    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            CountDownLatch latch = new CountDownLatch(1);
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });

            two.start();
            one.start();
            // 收到信号，两个线程同时执行
            latch.countDown();
            one.join();
            two.join();

            String result = "第"+i+"次（"+x+","+y+")";
            if (x == 0 && y == 0 ) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }

        }
    }
}
