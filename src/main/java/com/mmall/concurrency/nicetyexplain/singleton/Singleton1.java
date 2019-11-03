package com.mmall.concurrency.nicetyexplain.singleton;

/**
 * @Author mcg
 * @Date 2019/11/3 15:07
 *
 * 饿汉单例
 **/

public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();



    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
