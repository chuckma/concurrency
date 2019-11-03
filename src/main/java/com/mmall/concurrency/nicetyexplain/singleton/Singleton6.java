package com.mmall.concurrency.nicetyexplain.singleton;

/**
 * @Author mcg
 * @Date 2019/11/3 15:12
 * <p>
 * 双重检查 单例（推荐面试使用，可以考查到 JMM 相关） 兼顾性能和线程安全，懒加载
 * <p>
 * 优点：线程安全；延迟加载；效率高
 * <p>
 * 为什么要  dobule-check
 * 回答：首先线程安全，单次 check 为什么不安全? 【回答：第一次检查的时候如果有 2 个
 * 线程进入，就没法阻挡创建 2 个实例了，所以必须在 synchronized 内再一次 check】
 * 有的面试官可能会继续问，那如果把 synchronized 放在 getInstance() 这个方法上行不行
 * 是不是就只需要 check 一次了呢？ 【回答是：是可以的，这种情况是只需要check 一次就行了，
 * 但是会导致一个性能问题，如果多个线程过来的话，就会很影响性能】
 * <p>
 * <p>
 * 为什么需要加 volatile 关键字
 * 回答 新建对象不是一个原子操作，它分为 3 个步骤；（1.分配内存给对象；2.初始化对象；3.设置指向刚分配的内存地址）指令重排
 * 重排序会带来NPE；
 * 防止重排序
 **/

public class Singleton6 {

    private volatile static Singleton6 instance;

    private Singleton6() {

    }


    public static Singleton6 getInstance() {
        if (instance == null) {// 第一次检查
            synchronized (Singleton6.class) {
                if (instance == null) {// 第二次检查  双重检查
                    instance = new Singleton6();
                    // 上面这行代码， new Singleton6（） 这个可能发生指令重排，如果线程0发生指令重排
                    // 时间 |  线程0                                  线程1
                    // 时间 |  1. 分配对象的内存空间
                    //        3. 设置 instance指向内存空间
                    //                                          判断 instance 是否为 null（结果不为null 因为线程0 已经将 instance 指向内存空间）
//                                                              线程1初次访问对象   （此时 线程1 比 线程0 先访问对象，那此时就 NPE 了，因为instance 并没有完整的初始化）
                    //
                    //         2.初始化对象
                    // 时间 |   4. 线程0初次访问对象
                    // 时间 |
                }
            }
        }
        return instance;
    }
}
