package com.dorohedoro.thread.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockJoin {

    @SneakyThrows
    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        Thread son = new Thread(() -> {
            try {
                Thread.sleep(500);
                log.info("主线程状态: {}", main.getState());
                Thread.sleep(3000);
                log.info("子线程退出");
            } catch (InterruptedException e) {}
        });
        son.start();
        synchronized (son) {
            son.wait();
        }
        log.info("主线程退出");
    }
}
