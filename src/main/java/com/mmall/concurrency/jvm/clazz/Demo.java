package com.mmall.concurrency.jvm.clazz;

/**
 * @author mcg
 **/

public class Demo {

    static {
        System.out.println("main 类被加载了");
    }
    public static void main(String[] args) throws ClassNotFoundException {

        // 1 主动引用 初始化
//        Son son = new Son();
        // 反射也会 发生主动引用从而加载
//        Class.forName("com.mmall.concurrency.jvm.clazz.Son");

        //-----------------------------------------------//

        // 2 类的被动调用不会初始化
        // 访问静态域,只有声明这个域的地方会类会初始化
        /** 输出
         * main 类被加载了
         * 父类被加载了
         * 8
         */
//        System.out.println(Son.f);

        // 数组也不会产生初始化
        // 只输出 main 类被加载了.
//        Son[] s = new Son[5];

        // 调用常量也不会发生 常量在 链接阶段就被赋值了.
        System.out.println(Son.ss);

    }
}


class Father{
    static int f = 8;
    static {
        System.out.println("父类被加载了");
    }
}

class Son extends Father{
    static  int s = 5;
    static final int ss = 6;
    static {
        s = 55;
        System.out.println("子类被加载了");
    }
}
