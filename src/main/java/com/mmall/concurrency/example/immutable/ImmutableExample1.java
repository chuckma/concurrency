package com.mmall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created by Charles Date:2018/3/21
 * <p>
 * final 修饰基础类型数据, 不可被修改 , 若修饰类,被修饰的类不能被继承
 * 如果修饰引用类型的变量的话,不能再指向另外的一个对象
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;

    private final static String b = "2";

    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2 ;
//        b ="3";
//        map = Maps.newHashMap();
        map.put(1, 3);
        log.info("{}", map.get(1));

    }

    private void test(final int a) {
//        a = 1;
    }
}
