package com.mmall.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Author: lucasma
 *
 * CAS ABA 问题
 */
public class ThreadAtomicStampedReference {

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);
    final static AtomicInteger at = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final Integer stamp = money.getStamp();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Integer m = money.getReference();
                        Integer stamp = money.getStamp();
                        if (m < 20 && at.get() < 2) {
                            if (money.compareAndSet(m, m + 20, stamp, stamp + 1)) {
                                System.out.println("余额小于20,充值成功,当前余额:" + money.getReference());
                                at.getAndIncrement();
                            }
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Integer m = money.getReference();
                    Integer stamp = money.getStamp();
                    if (m > 10) {
                        System.out.println("金额大于10:" + m);
                        if (money.compareAndSet(m, m - 10, stamp, stamp + 1)) {
                            System.out.println("成功消费10元,剩余金额:" + money.getReference());
                        }
                    } else {
                        System.out.println("余额不足10元");
                    }
                }
            }
        }).start();
    }
}

