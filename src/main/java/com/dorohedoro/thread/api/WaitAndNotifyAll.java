package com.dorohedoro.thread.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitAndNotifyAll {

    @SneakyThrows
    public static void main(String[] args) {
        Object lock = new Object();
        Runnable r1 = () -> {
            synchronized (lock) {
                log.info("{}拿到了锁", Thread.currentThread().getName());
                try {
                    log.info("{}释放了锁", Thread.currentThread().getName());
                    lock.wait();
                } catch (InterruptedException e) {}
                log.info("{}被唤醒&拿到了锁", Thread.currentThread().getName());
            }
        };
        Runnable r2 = () -> {
            synchronized (lock) {
                log.info("唤醒");
                lock.notifyAll();
            }
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r1);
        Thread t3 = new Thread(r2);
        t1.start();
        t2.start();
        //Thread.sleep(100);
        t3.start();
    }
}
