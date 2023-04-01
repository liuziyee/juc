package com.dorohedoro.thread.singleton;

public class LazyManStaticInnerClass {
    
    private LazyManStaticInnerClass() {}
    
    private static class SingletonInstance {
        private static final LazyManStaticInnerClass INSTANCE = new LazyManStaticInnerClass();
    }

    public static LazyManStaticInnerClass getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
