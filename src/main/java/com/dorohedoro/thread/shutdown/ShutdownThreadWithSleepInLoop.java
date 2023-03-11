package com.dorohedoro.thread.shutdown;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShutdownThreadWithSleepInLoop {

    @SneakyThrows
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        log.info("{}", num);
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
