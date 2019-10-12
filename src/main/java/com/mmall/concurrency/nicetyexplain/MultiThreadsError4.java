package com.mmall.concurrency.nicetyexplain;

/**
 * @author Administrator
 *
 *
 * 描述：
 *  初始化未完毕的时候就 this 赋值，发生 发布溢出
 */
public class MultiThreadsError4 {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(105);
        if (point != null) {
            System.out.println(point);
        }
     }
}

class Point {
    private final int x, y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadsError4.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return x + " , " + y;
    }
}


class PointMaker extends Thread {

    @Override
    public void run() {
        try {
            new Point(1,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
