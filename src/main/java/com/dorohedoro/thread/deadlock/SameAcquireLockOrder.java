package com.dorohedoro.thread.deadlock;

import com.dorohedoro.thread.deadlock.TransferMoney.Account;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SameAcquireLockOrder implements Runnable {

    Account from;
    Account to;
    static final int amount = 0;
    static Object lock = new Object();

    public SameAcquireLockOrder(Account from, Account to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        int fromHash = from.hashCode();
        int toHash = to.hashCode();
        if (fromHash == toHash) {
            synchronized (lock) {
                synchronized (from) {
                    synchronized (to) {
                        transfer();
                    }
                }
            }
            return;
        }

        Account one = from;
        Account two = to;
        if (fromHash > toHash) {
            one = to;
            two = from;
        }
        synchronized (one) {
            synchronized (two) {
                transfer();
            }
        }
    }

    @SneakyThrows
    public void transfer() {
        if (from.balance < amount) return;
        from.balance -= amount;
        to.balance += amount;
        log.info("已转账{}", amount);
    }
    
    @SneakyThrows
    public static void main(String[] args) {
        Account nintendo = new Account(500);
        Account bandai = new Account(500);
        Thread nintendo2Bandai = new Thread(new SameAcquireLockOrder(nintendo, bandai), "nintendo2Bandai");
        Thread bandai2Nintendo = new Thread(new SameAcquireLockOrder(bandai, nintendo), "bandai2Nintendo");
        nintendo2Bandai.start();
        bandai2Nintendo.start();
        nintendo2Bandai.join();
        bandai2Nintendo.join();
        log.info("任天堂余额: {}", nintendo.balance);
        log.info("万代余额: {}", nintendo.balance);
    }
}
