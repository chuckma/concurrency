package com.mmall.concurrency.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author mcg
 * @date 2021/4/9 14:37
 **/

public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();

        for (Long i = 0L; i < 10_0000_0000; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();

        System.out.println("sum = " + sum + " 耗时 " + (end - start));
    }

    private static void test2() {
        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(0L, 10_0000_0000).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();

        System.out.println("sum = " + sum + " 耗时 " + (end - start));

    }

    private static void test3() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = pool.submit(task);
        Long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " 耗时 " + (end - start));

    }
}
