package com.mmall.concurrency.nicetyexplain.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author mcg
 * @Date 2019/9/17 21:23
 *
 *
 * 描述：
 *  每个 1 秒钟输出当前时间，被中断，观察。
 *  Thread.sleep()
 *  TimeUint.SECONDS.sleep()
 **/

public class SleepInterrupted implements Runnable{


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();
        Thread.sleep(6500);
        thread.interrupt();
    }


    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("我被中断了！ ");
                e.printStackTrace();
            }

        }
    }
}
