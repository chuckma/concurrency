package com.mmall.concurrency.example.threadPool;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author mcg
 * @Date 2019/3/21 20:15
 **/

public class ThreadPoolExample5 {

    /**
     *  corePoolSize 的大小设置 需要 两个值类决定 每秒的任务数 tasks,每个任务花费的时间 taskcost
     *  responsetime  ：系统允许容忍的最大响应时间
     *  corePollSize = 每秒需要多少个线程来处理 , 假设 tasks = 500-1000，taskcost = 0.1s ，responsetime 假设为1s
     *  threadCout = tasks / (1 / taskcost)  = tasks * taskcost = （500-1000） * 0.1 = 50 - 100 之间
     *  corePoolSize 设置应该大于50 根据8020原则，如果80%的每秒任务数小于800，那么 corePoolSize 设置为 80 即可
     *
     *  队列容量 queueCapacity = (coreSizePool/taskcost)*responsetime
     *  计算可得 queueCapacity = 80/0.1*1 = 80。意思是队列里的线程可以等待1s，超过了的需要新开线程来执行
     *                切记不能设置为Integer.MAX_VALUE，这样队列会很大，线程数只会保持在corePoolSize大小，当任务陡增时，不能新开线程来执行，响应时间会随之陡增。
     *           maxPoolSize = (max(tasks)- queueCapacity)/(1/taskcost)（最大任务数-队列容量）/每个线程每秒处理能力 = 最大线程数
     *                 计算可得 maxPoolSize = (1000-80)/10 = 92
     *           rejectedExecutionHandler：根据具体情况来决定，任务不重要可丢弃，任务重要则要利用一些缓冲机制来处理
     *           keepAliveTime和allowCoreThreadTimeout：采用默认通常能满足
     * ---------------------
     * 作者：星空dream
     * 来源：CSDN
     * 原文：https://blog.csdn.net/qq_17045385/article/details/79820847
     *
     */

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
        int corePoolSize = 8;
        int maximumPoolSize = 16;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5000);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        Future submit1 = null;
        for (int i = 1; i <= 10; i++) {
           /* MyTask task = new MyTask(String.valueOf(i));
            Future<?> submit = executor.submit(task);*/
//            executor.execute(task);
            NewTask newTask = new NewTask(String.valueOf(i));
             submit1 = executor.submit(newTask);
            try {
                System.out.println(submit1.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
//            System.out.println(submit.get());
        }
        executor.shutdown();
        System.out.println("***** "+submit1.get());
        ThreadPoolExecutor tpe = ((ThreadPoolExecutor) executor);

        while (true) {
            System.out.println();
            // System.out.println("队列 ："+workQueue.remainingCapacity());
            int queueSize = tpe.getQueue().size();
            System.out.println("当前排队线程数：" + queueSize);

            int activeCount = tpe.getActiveCount();
            System.out.println("当前活动线程数：" + activeCount);

            long completedTaskCount = tpe.getCompletedTaskCount();
            System.out.println("执行完成线程数：" + completedTaskCount);

            long taskCount = tpe.getTaskCount();
            System.out.println("总线程数：" + taskCount);

            System.out.println(tpe.getCorePoolSize());

            Thread.sleep(3000);
        }
        //System.in.read(); //阻塞主线程
    }

    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }

    }

    static class NewTask implements Callable{
        private String name;

        public NewTask(String name) {
            this.name = name;
        }
        @Override
        public String  call() throws Exception {
            System.out.println(this.toString() + "is running ！");
            Thread.sleep(3000);
            return "OK";
        }

        @Override
        public String toString() {
            return "NewTask [name = ]"+name+"]";
        }
    }
}

