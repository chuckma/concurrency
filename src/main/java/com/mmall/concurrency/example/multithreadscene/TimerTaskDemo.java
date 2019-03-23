package com.mmall.concurrency.example.multithreadscene;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by lucasma
 */
public class TimerTaskDemo {


    public static void main(String[] args) {
        ScheduledExecutorService ses=Executors.newScheduledThreadPool(2);
        ses.scheduleAtFixedRate(new MyTimerTask(), 2, 1, TimeUnit.SECONDS);
        ses.scheduleAtFixedRate(new MyTimerTask(), 1, 1, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(10);
            /*10秒后停止任务*/
            ses.shutdown();//停止运行线程池上的所有runable。
            System.out.print("--运行10秒停止--");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*TimerTask实现Runable接口*/
    static class MyTimerTask extends java.util.TimerTask {
        public void run() {
            System.out.println(Thread.currentThread().getName()+":"+ Calendar.getInstance().get(Calendar.SECOND));
        }
    }
}
