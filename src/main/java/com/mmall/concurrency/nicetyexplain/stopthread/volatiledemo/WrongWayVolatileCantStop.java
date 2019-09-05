package com.mmall.concurrency.nicetyexplain.stopthread.volatiledemo;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Administrator
 * <p>
 * 描述: 演示 volatile 的局限性 part2
 * 陷入阻塞时，volatile 是无法线程的
 * 此例中，生产者的生产速度很快，消费者消费速度慢
 * 所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue sorege = new ArrayBlockingQueue(10);
        Producer producer = new Producer(sorege);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(sorege);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);

        }
        System.out.println("消费者不需要更多数据了");

        // 一旦消费者不需更多数据，我们应该让生产者停止生产
        producer.canceld = true;
    }
}

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
            while (num <= 100000 && !canceld) {
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