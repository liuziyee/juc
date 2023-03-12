package com.dorohedoro.thread.api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintOddEvenUseSynchronized {

    static int num = 0;
    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (num <= 100) {
                synchronized (lock) {
                    if ((num & 1) == 0) {
                        log.info("偶数打印器: {}", num++);
                    }
                }
            }
        }).start();
        new Thread(() -> {
            while (num <= 100) {
                synchronized (lock) {
                    if ((num & 1) == 1) {
                        log.info("奇数打印器: {}", num++);
                    }
                }
            }
        }).start();
    }
}
