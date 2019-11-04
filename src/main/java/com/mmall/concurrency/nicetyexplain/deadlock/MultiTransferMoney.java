package com.mmall.concurrency.nicetyexplain.deadlock;

import java.util.Random;

/**
 * @author Administrator
 *
 * 多人同时转账，依然很危险，
 *
 * deadlock  4个必要条件
 * 1 互斥条件 （我用的时候别人就不能用）
 * 2 请求保持条件
 * 3 不剥夺条件 （不能有外界来干扰的）
 * 4 循环等待条件
 */
public class MultiTransferMoney {


    private static final int NUM_ACCOUNTS = 5000;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATIONS=1000000;
    private static final int NUM_THREADS=20;

    public static void main(String[] args) {
        Random rnd = new Random();

        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new TransferMoney.Account(NUM_MONEY);
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int  fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAcct],accounts[toAcct],amount);
                }
                System.out.println("运行结束");
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
             new TransferThread().start();
        }
    }

}
