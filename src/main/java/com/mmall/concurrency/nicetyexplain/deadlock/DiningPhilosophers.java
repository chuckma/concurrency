package com.mmall.concurrency.nicetyexplain.deadlock;

/**
 * @Author mcg
 * @Date 2019/11/5 21:05
 * <p>
 * 哲学家就餐问题导致的死锁
 **/

public class DiningPhilosophers {


    public static class Philosopher implements Runnable {
        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object lestChopstick, Object rightChopstick) {
            this.leftChopstick = lestChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Picked up left chopstick");
                        synchronized (rightChopstick) {
                            doAction("Picked up right chopstick -eating");
                            doAction("Put down right chopstick");
                        }
                        doAction("Put down left chopstick");
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep((long) (Math.random() * 10));
        }


        public static void main(String[] args) {
            Philosopher[] philosophers = new Philosopher[5];
            Object[] chopsticks = new Object[philosophers.length];
            for (int i = 0; i < chopsticks.length; i++) {
                chopsticks[i] = new Object();
            }
            for (int i = 0; i < philosophers.length; i++) {
                Object leftChopstick = chopsticks[i];
                Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];

                // 改变一个哲学家拿叉子的顺序（避免策略解决死锁）
                if (i == philosophers.length - 1) {
                    philosophers[i] = new Philosopher(rightChopstick, leftChopstick);

                } else {
                    philosophers[i] = new Philosopher(leftChopstick, rightChopstick);

                }


                new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();
            }
        }
    }


}
