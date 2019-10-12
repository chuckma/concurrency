package com.mmall.concurrency.nicetyexplain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 *  对象发布
 *  什么是发布？
 *  一个对象被超过这个类范围的地方使用，就是将其发布了，
 *  比如一个对象被声明为 public，那这对象就是被发布出去了；或者一个方法的 return 是一个对象的话，那么任何调用这个方法的类都获得了
 *  这个对象；或者把这个对象作为一个参数传到其他类的方法中，那么也是脱离了本类来到了其他类的方法，这些都是发布
 *
 *  发布溢出
 *
 *  发布溢出是对象被发布到了不该发布的地方，比如这个类的实例中的 private 的 map 对象；
 *
 *  有几个典型的发布溢出情况如下：
 *      1.方法返回一个 private 对象(private 本意是不让外部访问)
 *      2.还未完成初始化（构造函数没完全执行完毕）就把对象提供给外界 比如：
 *          在构造函数未初始化完毕就 this 赋值
 *          隐式溢出--注册监听事件
 *          构造函数中运行线程
 *
 *
 */
public class MultiThreadsError3 {

    private Map<String,String> states;
    public MultiThreadsError3() {
        states = new HashMap<>();
        states.put("1","1");
        states.put("2","2");
        states.put("3","3");
        states.put("4","4");
    }


    public Map<String, String> getStates() {
        return states;
    }

    public static void main(String[] args) {
        MultiThreadsError3 multiThreadsError3 = new MultiThreadsError3();


        Map<String, String> states = multiThreadsError3.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }
}
