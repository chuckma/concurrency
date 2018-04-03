package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.Recommend;
import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * Created by Charles Date:2018/3/19
 */

/**
 * 枚举模式:最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有的构造函数
    private SingletonExample7() {

    }

    // 静态工厂方法
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getIntance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getIntance() {
            return singleton;
        }
    }
}
