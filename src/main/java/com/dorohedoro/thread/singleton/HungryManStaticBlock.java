package com.dorohedoro.thread.singleton;

public class HungryManStaticBlock {

    private static final HungryManStaticBlock INSTANCE;
    
    static {
        INSTANCE = new HungryManStaticBlock();
    }
    
    private HungryManStaticBlock() {}

    public static HungryManStaticBlock getInstance() {
        return INSTANCE;
    }
}
