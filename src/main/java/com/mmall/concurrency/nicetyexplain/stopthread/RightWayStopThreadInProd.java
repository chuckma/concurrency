package com.mmall.concurrency.nicetyexplain.stopthread;

/**
 * @Author mcg
 * @Date 2019/9/4 20:07
 * <p>
 * 描述： 线程中断最佳实践
 * <p>
 * catch 了 InterruptedException 之后的优先选择：在方法签名中抛出异常
 **/

public class RightWayStopThreadInProd implements Runnable {


    @Override
    public void run() {
        while (true&&!Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                // 更多的逻辑操作，保存日志，停止程序等待
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
            Thread.sleep(2000);
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

    }
}
