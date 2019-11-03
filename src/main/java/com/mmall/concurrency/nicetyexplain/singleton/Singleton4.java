package com.mmall.concurrency.nicetyexplain.singleton;

/**
 * @Author mcg
 * @Date 2019/11/3 15:12
 * <p>
 * 懒汉单例 （线程安全,但是不推荐）
 * 缺点是效率太低了。
 **/

public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {

    }


    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
