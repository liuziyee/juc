package com.dorohedoro.thread.create;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BothThreadAndRunnable {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("执行Runnable的run()");
            }
        }) {
            @Override
            public void run() {
                log.info("执行Thread的run()");
            }
        }.start();
    }
}
