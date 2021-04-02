package com.mmall.concurrency.nicetyexplain.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mcg
 * @date 2021/4/2 09:33
 * <p>
 * 自定义的 UncaughtExceptionHandler
 **/

public class CustomUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {


    private String name;

    public CustomUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常,终止了" + t.getName(), e);
        System.out.println(name + "捕获了异常" + t.getName() + "异常" + e);
    }
}
