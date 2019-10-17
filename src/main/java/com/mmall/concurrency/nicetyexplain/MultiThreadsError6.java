package com.mmall.concurrency.nicetyexplain;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author mcg
 * @Date 2019/10/17 22:55
 * <p>
 * 描述：构造函数中新建线程
 *
 *  不应该在构造函数中用新开线程去做初始化的工作
 **/

public class MultiThreadsError6 {

    private Map<String, String> states;

    public MultiThreadsError6() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                states = new HashMap<>();
                states.put("1", "1");
                states.put("2", "2");
                states.put("3", "3");
                states.put("4", "4");

            }
        }).start();
    }

    public Map<String, String> getStates() {
        return states;
    }


    public static void main(String[] args) {
        MultiThreadsError6 multiThreadsError6 = new MultiThreadsError6();
        Map<String, String> states = multiThreadsError6.getStates();
        System.out.println(states.get("1"));
    }
}
