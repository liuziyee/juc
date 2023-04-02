package com.dorohedoro.thread.deadlock;

import com.dorohedoro.thread.deadlock.TransferMoney.Account;

import java.util.Random;
import java.util.stream.IntStream;

public class MultiTransferMoney {

    private static final int NUM_ACCOUNTS = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_LOOP = 1000000;
    private static final int NUM_THREADS = 50;

    public static void main(String[] args) {
        Random random = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        IntStream.range(0, NUM_ACCOUNTS).forEach(o -> accounts[o] = new Account(NUM_MONEY));
        Runnable runnable = () -> IntStream.range(0, NUM_LOOP).forEach(o -> {
            int from = random.nextInt(NUM_ACCOUNTS);
            int to = random.nextInt(NUM_ACCOUNTS);
            int amount = random.nextInt(NUM_MONEY);
            TransferMoney.transfer(accounts[from], accounts[to], amount);
        });
        IntStream.range(0, NUM_THREADS).forEach(o -> new Thread(runnable).start());
    }
}
