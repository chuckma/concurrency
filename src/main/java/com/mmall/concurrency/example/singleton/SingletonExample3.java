package com.mmall.concurrency.example.singleton;

/**
 * Created by Charles Date:2018/3/19
 */

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例的实例在第一次使用的时候创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有的构造函数
    private SingletonExample3(){

    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态工厂方法
    public static synchronized SingletonExample3 getInstance(){
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
