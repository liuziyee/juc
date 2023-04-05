package com.dorohedoro.thread.deadlock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

@Slf4j
public class LiveLock {

    public static class Philosopher implements Runnable {

        private Lock leftChopstick;
        private Lock rightChopstick;

        public Philosopher(Lock leftChopstick, Lock rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                doAction("思考");
                if (leftChopstick.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        doAction("取得左手边的筷子");
                        if (rightChopstick.tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                doAction("取得右手边的筷子");
                                doAction("吃饭");
                            } finally {
                                doAction("放下右手边的筷子");
                                rightChopstick.unlock();
                            }
                        } else {
                            doAction("尝试取得右手边的筷子失败");
                        } 
                    } finally {
                        doAction("放下左手边的筷子");
                        leftChopstick.unlock();
                    }
                } else {
                    doAction("尝试取得左手边的筷子失败");
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
        DiningPhilosophers.Philosopher[] philosophers = new DiningPhilosophers.Philosopher[5];
        Lock[] chopsticks = new Lock[philosophers.length];
        IntStream.range(0, 5).forEach(o -> chopsticks[o] = new ReentrantLock());
        for (int i = 0; i < philosophers.length; i++) {
            Lock left = chopsticks[i];
            Lock right = chopsticks[(i + 1) % chopsticks.length];
            Philosopher philosopher = new Philosopher(left, right);
            new Thread(philosopher, "哲学家" + i + "号").start();
        }
    }
}
