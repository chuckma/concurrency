package com.mmall.concurrency.function;

import java.util.function.Consumer;

/**
 * @author mcg
 *
 * 消费型接口
 **/

public class Demo3 {


    public static void main(String[] args) {



//        // 消费型接口
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };

        // 简化写法
        Consumer<String> consumer = s -> System.out.println(s);
//        Consumer<String> consumer = System.out::println;
        consumer.accept("12312");

    }
}
