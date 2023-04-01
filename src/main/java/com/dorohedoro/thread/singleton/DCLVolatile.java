package com.dorohedoro.thread.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DCLVolatile {

    private volatile static DCLVolatile INSTANCE;
    
    private DCLVolatile() {}

    public static DCLVolatile getInstance() {
        if (INSTANCE == null) {
            synchronized (DCLVolatile.class) {
                if (INSTANCE == null) {
                    log.info("new: 1.分配内存空间 2.实例化 3.将instance指向分配的内存空间");
                    log.info("volatile修饰单例对象可以避免出现1-3-2的执行顺序");
                    INSTANCE = new DCLVolatile();
                }
            }
        }
        return INSTANCE;
    }
}
