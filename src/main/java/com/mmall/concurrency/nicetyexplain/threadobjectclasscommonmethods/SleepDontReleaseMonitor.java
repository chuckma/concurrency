package com.mmall.concurrency.nicetyexplain.threadobjectclasscommonmethods;

/**
 * @Author mcg
 * @Date 2019/9/17 21:11
 *
 * 描述：
 * 展示线程 sleep 的时候不释放 synchronized 的monitor，等 sleep 时间
 * 到了以后，正常结束后才释放锁
 **/

public class SleepDontReleaseMonitor implements Runnable{


    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();


        new Thread((sleepDontReleaseMonitor)).start();
        new Thread((sleepDontReleaseMonitor)).start();
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了 monitor。");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + Thread.currentThread().getName() + "退出了同步代码块");

    }
}
