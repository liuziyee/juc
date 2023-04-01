package com.dorohedoro.thread.singleton;

public class HungryManStaticField {

    private static final HungryManStaticField INSTANCE = new HungryManStaticField();

    private HungryManStaticField() {}

    public static HungryManStaticField getInstance() {
        return INSTANCE;
    }
}
