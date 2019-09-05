package com.mmall.concurrency.nicetyexplain.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Administrator
 *
 *
 * 描述：用中断来修复刚才的无尽等待  代码见 WrongWayVolatileCantStop
 *
 */
public class WrongWayVolatileFixed {


    public static void main(String[] args) throws InterruptedException {

        WrongWayVolatileFixed body = new WrongWayVolatileFixed();

        ArrayBlockingQueue sorege = new ArrayBlockingQueue(10);
        Producer producer = body.new Producer(sorege);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = body.new Consumer(sorege);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);

        }
        System.out.println("消费者不需要更多数据了");

        // 一旦消费者不需更多数据，我们 应该让生产者停止生产,给出中断
        producerThread.interrupt();
    }

    // 内部类的方式
    class Producer implements Runnable {
        public volatile boolean canceld = false;
        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }
        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println(num + "是 100的倍数,被放到仓库了");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("生产者停止运行");
            }
        }
    }


    class Consumer {

        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }
}

