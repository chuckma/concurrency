package com.mmall.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class LockExample3 {
    /**
     * 当需要将 当前类 LockExample3 map 不暴露给外面, 所以手动封装一些方法供外部使用,
     * 同时我们也担心出现并发问题, 所以在这里我们使用读写锁,对读和写分别进行锁定.
     * ReentrantReadWriteLock 需要保证的是在没有任何读写锁的时候, 才可以执行写入操作,对数据的同步做的更多一些
     * 其实现的悲观读取,如果想获得写入锁的时候, 不可以有读锁在保持,这样就是说在写的时候,所有当前能做的事都做完了.
     * 所以就会有这一种情况, 当读取很多的时候,写入很少的时候,就会使得线程遭遇饥饿.读锁一直在保持, 所以写锁一直没法执行,处于等待状态
     *
     * 注: 实际场景并不多迟到
     */
    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value) {
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            readLock.unlock();
        }
    }

    class Data {

    }
}
