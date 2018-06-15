package com.mmall.concurrency.example.CollectionLoop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Administrator
 * @date 2017/6/15  10:09
 * <p>
 * 遍历HashMap
 */
public class HashMapLoop {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            map.put(i, i);
        }

        System.out.println(test1(map));

    }

    private static double test1(Map map){
        Iterator it = map.entrySet().iterator();
        Long startTime = System.currentTimeMillis();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            //System.out.println(key+"++++"+value);
        }

        Long endTime = System.currentTimeMillis();
        return (endTime-startTime);
    }
}
