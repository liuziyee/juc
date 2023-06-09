package com.dorohedoro.thread.api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinInterrupt {

    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        Thread son = new Thread(() -> {
            try {
                Thread.sleep(500);
                log.info("主线程状态: {}", main.getState());
                main.interrupt();
                Thread.sleep(3000);
                log.info("子线程退出");
            } catch (InterruptedException e) {}
        });
        son.start();
        try {
            son.join();
        } catch (InterruptedException e) {
            log.info("主线程中断");
        }
        log.info("主线程退出");
    }
}
