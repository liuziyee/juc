package com.dorohedoro.thread.synchronize;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedIsRLock {

    @SneakyThrows
    public synchronized void sleep() {
        log.info(Thread.currentThread().getName());
        Thread.sleep(1000);
        sleep();
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {

            SynchronizedIsRLock lock = new SynchronizedIsRLock();
            
            @Override
            public void run() {
                lock.sleep();
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
