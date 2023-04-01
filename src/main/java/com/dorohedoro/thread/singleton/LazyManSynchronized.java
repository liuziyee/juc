package com.dorohedoro.thread.singleton;

public class LazyManSynchronized {

    private static LazyManSynchronized INSTANCE;

    private LazyManSynchronized() {}

    public synchronized static LazyManSynchronized getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyManSynchronized();
        }
        return INSTANCE;
    }
}
