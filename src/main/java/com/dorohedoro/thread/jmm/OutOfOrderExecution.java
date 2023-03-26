package com.dorohedoro.thread.jmm;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class OutOfOrderExecution {

    static int x, y, a, b;

    @SneakyThrows
    public static void main(String[] args) {
        for (; ; ) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;

            CountDownLatch latch = new CountDownLatch(3);

            Thread t1 = new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {}
                a = 1;
                x = b;
            });
            Thread t2 = new Thread(() -> {
                try {
                    latch.countDown();
                    latch.await();
                } catch (InterruptedException e) {}
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            latch.countDown();
            t1.join();
            t2.join();

            log.info("x={}, y={}", x, y);
            if (x == 0 && y == 0) {
                break;
            }
        }
    }
}
