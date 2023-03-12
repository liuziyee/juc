package com.dorohedoro.thread.api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintOddAndEvenUseWaitNotify {

    static int num = 0;
    static Object lock = new Object();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (num <= 100) {
                synchronized (lock) {
                    log.info("{}", num++);
                    lock.notify();
                    try {
                        if (num <= 100) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {}
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
