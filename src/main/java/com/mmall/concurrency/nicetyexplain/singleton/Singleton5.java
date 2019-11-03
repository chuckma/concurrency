package com.mmall.concurrency.nicetyexplain.singleton;

/**
 * @Author mcg
 * @Date 2019/11/3 15:12
 * <p>
 * 懒汉单例 （线程不安全,不推荐）
 **/

public class Singleton5 {

    private static Singleton5 instance;

    private Singleton5() {

    }


    public static Singleton5 getInstance() {
        if (instance == null) {
            // 一旦 2 个线程同时进入这个 if 里面，谁也没法阻挡他们创建 2 个实例
            synchronized (Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
