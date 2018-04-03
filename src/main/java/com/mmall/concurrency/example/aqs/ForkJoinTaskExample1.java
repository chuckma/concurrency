package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;


/**
 * Created by Charles
 * RecursiveTask 字面意思就是递归任务 把大的任务拆分成小任务
 *
 */
@Slf4j
public class ForkJoinTaskExample1 extends RecursiveTask<Integer> {

    public static final int threshold = 100;
    private int start;
    private int end;

    public ForkJoinTaskExample1(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTaskExample1 leftTask = new ForkJoinTaskExample1(start, middle);
            ForkJoinTaskExample1 rightTask = new ForkJoinTaskExample1(middle + 1, end);

            invokeAll(leftTask, rightTask);

            // 执行子任务
//            leftTask.fork();
//            rightTask.fork();

            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkjoinPool = new ForkJoinPool(4);

        //生成一个计算任务，计算1+2+3+4    从 1一直加到 400
        ForkJoinTaskExample1 task = new ForkJoinTaskExample1(1, 400);

        long startTime = System.currentTimeMillis();
        //执行一个任务
//        Future<Integer> result = forkjoinPool.submit(task);

        Integer result = forkjoinPool.invoke(task);

        long endTime = System.currentTimeMillis();
        try {
            log.info("result:{}, in:{}", result,endTime-startTime);
        } catch (Exception e) {
            log.error("exception", e);
        }
    }
}
