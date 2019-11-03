package com.mmall.concurrency.nicetyexplain.singleton;

/**
 * @Author mcg
 * @Date 2019/11/3 15:12
 * <p>
 * 静态内部类方式单例 ，可用
 **/

public class Singleton7 {


    private Singleton7() {

    }


    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;

    }
}
