package com.mmall.concurrency.nicetyexplain.stopthread;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author mcg
 * @Date 2019/9/4 19:58
 * <p>
 * 描述 ：如果 while 里面放 try catch ，会导致中断失效
 **/

public class CantInterrupt {


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;
            while (num <= 10000&&!Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num+ "是 100的 整数");
                }
                num++;

                try {
                    Thread.sleep(10);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

    }
}
