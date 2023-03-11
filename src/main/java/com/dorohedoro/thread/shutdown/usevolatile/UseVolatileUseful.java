package com.dorohedoro.thread.shutdown.usevolatile;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UseVolatileUseful implements Runnable {

    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (!canceled && num <= 10000) {
                if (num % 100 == 0) {
                    log.info("{}", num);
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        UseVolatileUseful runnable = new UseVolatileUseful();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        runnable.canceled = true;
    }
}
