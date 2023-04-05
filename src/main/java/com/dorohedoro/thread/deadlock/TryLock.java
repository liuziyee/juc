package com.dorohedoro.thread.deadlock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class TryLock implements Runnable {

    Lock one;
    Lock two;
    long timeout;

    public TryLock(Lock one, Lock two, long timeout) {
        this.one = one;
        this.two = two;
        this.timeout = timeout;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (; ; ) {
            String no = Thread.currentThread().getName();
            if (one.tryLock(timeout, TimeUnit.SECONDS)) {
                try {
                    log.info("{}取得{}", no, one);
                    Thread.sleep(new Random().nextInt(1000));
                    if (two.tryLock(timeout, TimeUnit.SECONDS)) {
                        try {
                            log.info("{}取得{}", no, two);
                        } finally {
                            log.info("{}释放{}", no, two);
                            two.unlock();
                        }
                    } else {
                        log.info("{}尝试取得{}失败", no, two);
                    }
                } finally {
                    log.info("{}释放{}", no, one);
                    one.unlock();
                    // 这里的睡眠是为了让其他线程取得这把锁
                    Thread.sleep(new Random().nextInt(1000));
                }
            } else {
                log.info("{}尝试取得{}失败", no, one);
            }
        }
    }

    public static void main(String[] args) {
        Lock one = new ReentrantLock();
        Lock two = new ReentrantLock();
        new Thread(new TryLock(one, two, 1)).start();
        new Thread(new TryLock(two, one, 3)).start();
    }
}