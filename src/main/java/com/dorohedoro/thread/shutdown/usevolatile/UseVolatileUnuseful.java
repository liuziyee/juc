package com.dorohedoro.thread.shutdown.usevolatile;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class UseVolatileUnuseful {

    @SneakyThrows
    public static void main(String[] args) {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(10);

        Producer producer = new Producer(queue);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(queue);
        while (consumer.needMore()) {
            log.info("已消费{}", consumer.queue.take());
            Thread.sleep(100);
        }
        log.info("消费者停止消费");
        producer.canceled = true;
    }
}

@Slf4j
class Producer implements Runnable {

    volatile boolean canceled = false;

    BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @SneakyThrows
    @Override
    public void run() {
        int num = 0;
        while (!canceled && num <= 100000) {
            if (num % 100 == 0) {
                log.info("已生产{}", num);
                queue.put(num);
            }
            num++;
        }
    }
}

class Consumer {

    BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public boolean needMore() {
        return Math.random() <= 0.95;
    }
}
