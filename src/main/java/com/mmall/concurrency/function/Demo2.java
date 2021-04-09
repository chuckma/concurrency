package com.mmall.concurrency.function;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author mcg
 * <p>
 * 判断型接口 函数式接口的一种
 **/

public class Demo2 {


    public static void main(String[] args) {



//        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };
        // 简化写法
        Predicate<String> predicate = s -> s.isEmpty();
        System.out.println(predicate.test("123"));
    }
}
