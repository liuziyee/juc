package com.dorohedoro.thread.api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinInterrupt {

    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(500);
                log.info("主线程状态: {}", main.getState());
                main.interrupt();
                Thread.sleep(3000);
                log.info("子线程结束");
            } catch (InterruptedException e) {}
        });
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            log.info("主线程中断");
        }
        log.info("主线程结束");
    }
}
