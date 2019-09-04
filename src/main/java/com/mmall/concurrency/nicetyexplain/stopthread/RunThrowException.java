package com.mmall.concurrency.nicetyexplain.stopthread;

/**
 * @Author mcg
 * @Date 2019/9/4 20:19
 * <p>
 * run 方法无法抛出 checked exception ，只能用 try/catch
 **/

public class RunThrowException {


    public void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
