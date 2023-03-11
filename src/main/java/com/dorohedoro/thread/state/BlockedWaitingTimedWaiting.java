package com.dorohedoro.thread.state;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockedWaitingTimedWaiting implements Runnable {
    
    @Override
    @SneakyThrows
    public void run() {
        synchronized (this) {
            Thread.sleep(1000);
            wait();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread t2 = new Thread(runnable);
        t2.start();

        Thread.sleep(5);
        log.info("{}", t1.getState());
        log.info("{}", t2.getState());
        
        Thread.sleep(1500);
        log.info("{}", t1.getState());
    }
}
