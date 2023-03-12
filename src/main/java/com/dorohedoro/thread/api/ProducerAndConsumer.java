package com.dorohedoro.thread.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.LinkedList;

public class ProducerAndConsumer {

    public static void main(String[] args) {
        Queue queue = new Queue();
        new Thread(() -> {
            while (true) {
                queue.put();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                queue.get();
            }
        }).start();
    }
}

@Slf4j
class Queue {
    int maxSize = 10;
    LinkedList queue = new LinkedList<Date>();

    @SneakyThrows
    public synchronized void put() {
        while (queue.size() == maxSize) {
            wait();
        }
        Date date = new Date();
        queue.offer(date);
        log.info("生产了{},队列长度为{}", date, queue.size());
        notify();
    }

    @SneakyThrows
    public synchronized void get() {
        while (queue.isEmpty()) {
            wait();
        }
        log.info("消费了{},队列长度为{}", queue.poll(), queue.size());
        notify();
    }
}