package com.mmall.concurrency.nicetyexplain.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author mcg
 * @Date 2019/9/17 21:17
 *
 * 描述：
 *  演示 sleep 不释放 lock (lock 需要手动释放)
 **/

public class SleepDontReleaseLock  implements Runnable{


    private static final Lock lock = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        System.out.println("线程" + Thread.currentThread().getName() + "获取了锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程" + Thread.currentThread().getName() + "已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        SleepDontReleaseLock sleepDontReleaseLock = new SleepDontReleaseLock();
        new Thread(sleepDontReleaseLock).start();
        new Thread(sleepDontReleaseLock).start();

    }
}
