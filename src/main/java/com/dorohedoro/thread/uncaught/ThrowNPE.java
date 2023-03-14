package com.dorohedoro.thread.uncaught;

public class ThrowNPE {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtProblemHandler());
        new Thread(() -> {
            throw new NullPointerException();
        }).start();
    }
}
