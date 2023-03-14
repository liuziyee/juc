package com.dorohedoro.thread.uncaught;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UncaughtProblemHandler implements Thread.UncaughtExceptionHandler{
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, t.getName() + "退出, 错误: " + e.getMessage());
    }
}
