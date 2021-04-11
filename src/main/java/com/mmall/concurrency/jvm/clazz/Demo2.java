package com.mmall.concurrency.jvm.clazz;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * @author mcg
 * @date 2021/4/11 19:44
 **/

public class Demo2 {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("os.name"));
        new String();

    }
}
