package com.dorohedoro.thread.shutdown;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterruptAPI {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {}
        });

        thread.start();
        thread.interrupt();
        log.info("{}", thread.isInterrupted());
        log.info("{}", thread.interrupted());
        log.info("{}", Thread.interrupted());
        log.info("{}", thread.isInterrupted());
    }
}
