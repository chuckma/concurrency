package com.mmall.concurrency.jvm.clazz;

/**
 * @author mcg
 **/

public class User {


    private String name;
    private int num;
    private int age;

    public User() {
    }

    public User(String name, int num, int age) {
        this.name = name;
        this.num = num;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", age=" + age +
                '}';
    }
}
