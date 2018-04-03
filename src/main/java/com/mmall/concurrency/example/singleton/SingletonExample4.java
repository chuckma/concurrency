package com.mmall.concurrency.example.singleton;

/**
 * Created by Charles Date:2018/3/19
 */

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式 --> 双重同步锁单例模式
 * 单例的实例在第一次使用的时候创建
 */
@NotThreadSafe
public class SingletonExample4 {

    // 私有的构造函数
    private SingletonExample4(){

    }

    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态工厂方法
    public static  SingletonExample4 getInstance(){
        if (instance == null) { // 双重检测机制
            synchronized(SingletonExample4.class){ //同步锁
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
