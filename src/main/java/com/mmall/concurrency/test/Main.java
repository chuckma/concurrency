package com.mmall.concurrency.test;

import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/7/3  10:30
 */
public class Main {

    public static void main(String[] args) {
//        String str = "[{\"personid\":1,\"personname\":\"阿三\",\"personphone\":\"13588824444\"},{\"personid\":2,\"personname\":\"licas\",\"personphone\":\"18999002897\"},]";
//        JSONArray json = JSONArray.fromObject(str);
//        System.out.println(json);
        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setPassword("21341");
        user1.setUserName("test1");
        users.add(user1);

        User user2 = new User();
        user2.setPassword("21341");
        user2.setUserName("test2");
        users.add(user2);

        User user3 = new User();
        user3.setPassword("21341");
        user3.setUserName("test3");
        users.add(user3);
        User user4 = new User();
        user4.setPassword("21341");
        user4.setUserName("test4");
        users.add(user4);

    }
}
