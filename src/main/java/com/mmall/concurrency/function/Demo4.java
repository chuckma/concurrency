package com.mmall.concurrency.function;

import java.util.function.Supplier;

/**
 * @author mcg
 *
 * 供给型接口
 **/

public class Demo4 {


    public static void main(String[] args) {



//        Supplier supplier = new Supplier() {
//            @Override
//            public Object get() {
//                return 12312;
//            }
//        };


        // 一行的时候这样写
        Supplier supplier = () -> 12312;

        System.out.println(supplier.get());
    }
}
