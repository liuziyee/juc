package com.dorohedoro.thread.state;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

@Slf4j
public class NewRunnableTerminated implements Runnable {

    @Override
    public void run() {
        IntStream.range(0, 1000).forEach(o -> log.info("{}", o));
    }

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        log.info("{}", thread.getState());

        thread.start();
        
        Thread.sleep(10);
        log.info("{}", thread.getState());

        Thread.sleep(100);
        log.info("{}", thread.getState());
    }
}
