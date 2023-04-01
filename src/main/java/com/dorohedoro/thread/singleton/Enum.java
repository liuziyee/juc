package com.dorohedoro.thread.singleton;

public class Enum {
    
    private enum SingletonEnum {
        INSTANCE;

        private final Enum instance;
        
        SingletonEnum() {
            instance = new Enum(); 
        }

        private Enum getInstance() {
            return instance;
        }
    }
    
    private Enum() {}
    
    public static Enum getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }
}
