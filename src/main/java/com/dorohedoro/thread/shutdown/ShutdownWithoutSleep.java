package com.dorohedoro.thread.shutdown;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShutdownWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                log.info("{}", num);
            }
            num++;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(new ShutdownWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
