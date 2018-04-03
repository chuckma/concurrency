package com.mmall.concurrency.example.singleton;

/**
 * Created by Charles Date:2018/3/19
 */

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例的实例在类装载的时候创建
 *
 * 使用饿汉模式要注意两个问题
 *      1.其私有构造函数在实现的时候没有太多的处理,否则可能会造成性能的问题
 *      2.这个类在实际的过程中肯定会被使用, 不会造成资源的浪费.
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有的构造函数
    private SingletonExample2(){

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
