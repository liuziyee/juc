package com.dorohedoro.thread.jmm;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldVisibility {

    static int a = 1;
    volatile static int b = 2;
    volatile static boolean done = false;

    @SneakyThrows
    public static void main(String[] args) {
        for (; ; ) {
            a = 1;
            b = 2;

            Runnable writer = () -> {
                a = 3;
                b = a;
                done = true;
            };
            Runnable reader = () -> {
                while (!done) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                log.info("b={}, a={}", b, a);
            };
            new Thread(writer).start();
            new Thread(reader).start();
        }
    }
}
