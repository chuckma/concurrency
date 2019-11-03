package com.mmall.concurrency.nicetyexplain.singleton;

/**
 * @Author mcg
 * @Date 2019/11/3 15:12
 * <p>
 * 懒汉单例 （线程不安全）
 **/

public class Singleton3 {

    private static Singleton3 instance;

    private Singleton3() {

    }


    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
