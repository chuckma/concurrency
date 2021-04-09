package com.mmall.concurrency.function;

import java.util.function.Function;

/**
 * @author mcg
 * <p>
 * 函数式接口
 **/

public class Demo1 {


    public static void main(String[] args) {
        Function function = str -> {
            return str;
        };

        System.out.println(function.apply(123));
    }
}
