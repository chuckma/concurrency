package com.mmall.concurrency.nicetyexplain.sixstates;

/**
 * @Author mcg
 * @Date 2019/9/6 18:59
 * <p>
 * <p>
 * 描述：展示 Blocked Waiting Timed_Waiting
 **/

public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        // 打印出 Timed_Waiting 状态，因为正在执行Thread.sleep(1000)
        System.out.println(thread1.getState());
        // 打印出 Blocked 状态，因为thread 想拿到 sycn()的 锁却拿不到。
        System.out.println(thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印 Waiting
        System.out.println(thread2.getState());

    }

    @Override
    public void run() {
        syn();
    }


    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
