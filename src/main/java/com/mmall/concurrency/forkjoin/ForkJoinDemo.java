package com.mmall.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author mcg
 * @date 2021/4/9 14:54
 **/

public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    private long temp = 1000;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (end - start) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
