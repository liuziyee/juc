package com.dorohedoro.thread.shutdown;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShutdownWithSleep {

    @SneakyThrows
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (!Thread.currentThread().isInterrupted() && num <= 300) {
                    if (num % 100 == 0) {
                        log.info("{}", num);
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(300);
        thread.interrupt();
    }
}
