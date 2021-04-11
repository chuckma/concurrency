package com.mmall.concurrency.jvm.clazz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mcg
 * @date 2021/4/11 21:02
 **/

public class Test {


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName("com.mmall.concurrency.jvm.clazz.User");

        User user = (User) clazz.newInstance();
        Method setName = clazz.getDeclaredMethod("setName", String.class);
        setName.invoke(user, "李四");
        System.out.println(user);
        test1();
        test2();
        test3();
    }

    public static void test1() {
        User user = new User();
        long s = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            user.getName();
        }
        long e = System.currentTimeMillis();
        System.out.println("普通跑 10亿 次耗时" + (e - s) + " 毫秒");
    }

    public static void test2() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName("com.mmall.concurrency.jvm.clazz.User");

        User user = (User) clazz.newInstance();

        Method getName = clazz.getDeclaredMethod("getName", null);
        long s = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            getName.invoke(user,null);
        }
        long e = System.currentTimeMillis();
        System.out.println("反射未关闭检查 10亿 次耗时" + (e - s) + " 毫秒");
    }

    public static void test3() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName("com.mmall.concurrency.jvm.clazz.User");

        User user = (User) clazz.newInstance();

        Method getName = clazz.getDeclaredMethod("getName", null);
        getName.setAccessible(true);
        long s = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            getName.invoke(user,null);
        }
        long e = System.currentTimeMillis();
        System.out.println("反射关闭检查 10亿 次耗时" + (e - s) + " 毫秒");
    }
}
