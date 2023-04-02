package com.dorohedoro.thread.deadlock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransferMoney implements Runnable {

    Account from;
    Account to;

    public TransferMoney(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        transfer(from, to, 0);
    }
    
    @SneakyThrows
    public static void transfer(Account from, Account to, int amount) {
        synchronized (from) {
            log.info("{}拿到了锁{}", Thread.currentThread().getName(), from);
            Thread.sleep(500);
            synchronized (to) {
                if (from.balance < amount) return;
                from.balance -= amount;
                to.balance += amount;
                log.info("已转账{}", amount);
            }
        }
    }
    
    public static class Account {
        
        private int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Account nintendo = new Account(500);
        Account bandai = new Account(500);
        Thread nintendo2Bandai = new Thread(new TransferMoney(nintendo, bandai), "nintendo2Bandai");
        Thread bandai2Nintendo = new Thread(new TransferMoney(bandai, nintendo), "bandai2Nintendo");
        nintendo2Bandai.start();
        bandai2Nintendo.start();
        nintendo2Bandai.join();
        bandai2Nintendo.join();
        log.info("任天堂余额: {}", nintendo.balance);
        log.info("万代余额: {}", nintendo.balance);
    }
}
