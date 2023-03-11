package com.dorohedoro.thread.create;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ByRunnable implements Runnable {

    @Override
    public void run() {}

    public static void main(String[] args) {
        new Thread(new ByRunnable()).start();
    }
}
