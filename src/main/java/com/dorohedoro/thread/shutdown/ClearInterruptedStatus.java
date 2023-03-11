package com.dorohedoro.thread.shutdown;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClearInterruptedStatus {

    @SneakyThrows
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            while (!Thread.currentThread().isInterrupted() && num <= 10000) {
                if (num % 100 == 0) {
                    log.info("{}", num);
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.info("抛出InterruptedException,同时会清除掉中断状态");
                    log.info("\u001B[32m{}", e.getMessage());
                    Thread.currentThread().interrupt(); // 恢复中断
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
