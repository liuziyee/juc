package com.dorohedoro.thread.shutdown.usevolatile;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class UseVolatileUnusefulFixed {

    @SneakyThrows
    public static void main(String[] args) {
        UseVolatileUnusefulFixed outer = new UseVolatileUnusefulFixed();
        ArrayBlockingQueue queue = new ArrayBlockingQueue(10);

        Producer producer = outer.new Producer(queue);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = outer.new Consumer(queue);
        while (consumer.needMore()) {
            log.info("已消费{}", consumer.queue.take());
            Thread.sleep(100);
        }
        log.info("消费者退出");
        producerThread.interrupt();
    }

    class Producer implements Runnable {

        BlockingQueue queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                int num = 0;
                while (!Thread.currentThread().isInterrupted() && num <= 100000) {
                    if (num % 100 == 0) {
                        log.info("已生产{}", num);
                        queue.put(num);
                    }
                    num++;
                }
            } catch (InterruptedException e) {
            } finally {
                log.info("生产者退出");
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
}
