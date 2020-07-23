package com.mmall.concurrency.jvm;

import java.util.List;
import java.util.Vector;

/**
 * @author mcg
 *
 *  1. 锁分级 jvm 在设计 synchronized 的时候，做了锁的分级，分成了偏向锁，轻量级锁，重量级锁
 *  通过锁的分级，可以在竞争不激烈的情况下，让锁停留在偏向锁或者轻量级锁的级别，这样可以大幅度提升性能
 *  2. 锁消除 jvm 提供了锁消除，可以在特定的情况下降锁消除
 *  3. 锁粗化 jvm 提供了锁粗化，可以在特定的情况下将锁粗化 比如本类的 main 函数 for 循环加锁解锁
 */
public class SynchronizedTest5 {


    /**
     * 锁粗化就是将锁的作用范围放大
     * 这里 就是将 synchronized 放在 for 外面，
     * @param args
     */
    public static void main(String[] args) {
        // -server -XX:+DoEscapeAnalysis(开启逃逸分析，默认就是开启的)-XX:+EliminateLocks(开启锁粗化和锁消除) 3047
        // -server -XX:—DoEscapeAnalysis(关闭逃逸分析，默认是开启的)-XX:-EliminateLocks(关闭锁粗化和锁消除) 3178

        // 经过测试，锁消除和锁粗化会对 synchronized 的性能有所提升
        List<Integer> list = new Vector<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            // 这段代码连续的加锁解锁，jvm 会自动的进行粗化操作；
            // 实际在编写代码的时候，我们也可以对 synchronized 锁进行粗化，比如 method1 的代码
            synchronized (list) {
                list.add(i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private final Object lock = new Object();

    // 粗化前
    public void method1() {
        synchronized (lock) {
            // do some thing
        }

        // 能很快的执行完毕，且无需同步的代码
        synchronized (lock) {
            // do some thing
        }
    }

    // 粗化后
    public void method2() {
        //  进行锁粗化，整合成一次的锁请求，同步，释放
        synchronized (lock) {
            // do some thing

            // 能很快的执行完毕，且无需同步的代码
            // do some thing
        }
    }


    /**
     *  锁消除示例代码
     *  要知道出现线程安全的问题是有两点
     *  1. 存在共享数据
     *  2. 多线程同时操作共享数据
     *  下面这个方法里 obj 是局部变量，不是共享数据，所以多个线程执行 method3，也不会有线程安全的问题
     *  jdk 通过逃逸分析检测到这段代码不可能存在共享数据，也不可能存在竞争，就会自动消除掉这个锁
     *
     *  什么是逃逸分析?
     *  逃逸分析是指分析变量是否能逃出它的作用域，这个代码里的 obj是局部变量，方法执行完毕就没了，所以它不可逃逸
     *  可逃逸的代码示例在　method5
     *
     *  */
    private static void method3() {
        Object obj = new Object();
        synchronized (obj) {
            System.out.println(obj);
        }
    }


    /**
     * object 这个成员变量可能被多个线程操作
     */
    private Object object = null;
    public  void method4() {
        method5();
    }
    // obj　被返回了
    private static Object method5() {
        Object obj = new Object();
        synchronized (obj) {
            return obj;
        }
    }

}
