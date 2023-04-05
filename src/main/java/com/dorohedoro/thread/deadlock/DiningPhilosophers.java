package com.dorohedoro.thread.deadlock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class DiningPhilosophers {

    public static class Philosopher implements Runnable {

        private Object leftChopstick;
        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            while (true) {
                doAction("思考");
                synchronized (leftChopstick) {
                    doAction("取得左手边的筷子");
                    synchronized (rightChopstick) {
                        doAction("取得右手边的筷子");
                        doAction("吃饭");
                        doAction("放下右手边的筷子");
                    }
                    doAction("放下左手边的筷子");
                }
            }
        }
        
        @SneakyThrows
        public void doAction(String action) {
            log.info("{}{}", Thread.currentThread().getName(), action);
            Thread.sleep((long)Math.random() * 10);
        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsticks = new Object[philosophers.length];
        IntStream.range(0, 5).forEach(o -> chopsticks[o] = new Object());
        for (int i = 0; i < philosophers.length; i++) {
            Object left = chopsticks[i];
            Object right = chopsticks[(i + 1) % chopsticks.length];
            Philosopher philosopher = new Philosopher(left, right);

            //Philosopher philosopher;
            // 最少一位哲学家取得筷子的次序相反
            //if (i == philosophers.length - 1) {
            //    philosopher = new Philosopher(right, left);
            //} else {
            //    philosopher = new Philosopher(left, right);
            //}
            // 奇偶数编号取得筷子的次序相反
            //if (i % 2 == 0) {
            //    philosopher = new Philosopher(left, right);
            //} else {
            //    philosopher = new Philosopher(right, left);
            //}
            
            new Thread(philosopher, "哲学家" + i + "号").start();
        }
    }
}