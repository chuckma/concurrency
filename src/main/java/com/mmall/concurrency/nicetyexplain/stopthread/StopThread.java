package com.mmall.concurrency.nicetyexplain.stopthread;

/**
 * @author Administrator
 *
 * 错误的线程停止方式
 * stop() 方法会导致线程运行到一半突然停止，没法完成一个基本单元的操作，会造成造数据
 *
 * 我们模拟一个连队领取武器的例子，注意一个连队只能整体转移，stop() 中断在这里会导致
 * 某个连队里的某个士兵无法领取武器。
 *
 * 如果换在一个银行转账的场景，加入有 10 笔 款项需要转，如果转到 第 8 笔，程序突然停止
 * 那这样造成的数据错乱是很危险的，是很难排查出的。
 *
 * 另外还有两个错误的线程停止方式
 *
 * suspend 这种让线程挂起，它是带着锁休息的，不释放锁，这样就会很容易造成死锁
 * resume
 */
public class StopThread implements Runnable{
    @Override
    public void run() {

        // 模拟连队，总共 5 个连队，每队 10 人
        for (int i = 0; i < 5; i++) {
            System.out.println("连队"+ i +"开始领取武器");

            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("连队" + i + "已经完成武器领取");
        }
    }


    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
            // 一秒后突然接到战斗指令，赶赴前线战斗
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 停止武器领取，直接赶赴前线，可能前线有武器
        thread.stop();

    }
}
