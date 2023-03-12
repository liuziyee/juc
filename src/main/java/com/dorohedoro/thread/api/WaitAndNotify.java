package com.dorohedoro.thread.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitAndNotify {

    @SneakyThrows
    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {}
                for (; ; ) {} 
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                lock.notify();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
            }
        });

        t1.start();
        Thread.sleep(100);
        log.info("{}", t1.getState());
        t2.start();
        Thread.sleep(100);
        log.info("{}", t1.getState());
        Thread.sleep(5000);
        log.info("{}", t1.getState());
    }
}
