package bank;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private int balance;
    private AtomicInteger failCounter = new AtomicInteger(0);
    private Lock lock;

    public Lock getLock() {
        return lock;
    }

    public Account(int initialBalance) {
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

    public void incFailedTransferCount() {
        failCounter.incrementAndGet();
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
}
