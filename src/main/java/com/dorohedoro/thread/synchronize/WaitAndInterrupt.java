package com.dorohedoro.thread.synchronize;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitAndInterrupt {

    public synchronized void waiting() {
        try {
            this.wait();
        } catch (InterruptedException e) {}
        for (; ; ) {}
    }

    @SneakyThrows
    public synchronized void sleep() {
        Thread.sleep(1000);
        sleep();
    }

    @SneakyThrows
    public static void main(String[] args) {
        WaitAndInterrupt lock = new WaitAndInterrupt();
        Thread waitingThread = new Thread(() -> lock.waiting());
        Thread sleepThread = new Thread(() -> lock.sleep());

        waitingThread.start();
        Thread.sleep(100);
        sleepThread.start();
        waitingThread.interrupt();
        Thread.sleep(100);
        log.info("{}", waitingThread.getState());
    }
}
