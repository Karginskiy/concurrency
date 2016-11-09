package bank;

import java.util.concurrent.Callable;

public class Transfer implements Callable<Boolean> {

    private Account account1;
    private Account account2;
    private int amount;

    public Transfer(Account account1, Account account2, int amount) {
        this.account1 = account1;
        this.account2 = account2;
        this.amount = amount;
    }

    @Override
    public Boolean call() throws Exception {
        if (account1.getLock().tryLock()) {
            try {
                if (account2.getLock().tryLock()) {
                    try {
                        account1.withdraw(amount);
                        account2.deposit(amount);
                        return true;
                    } finally {
                        account2.getLock().unlock();
                    }
                } else {
                    return false;
                }
            } finally {
                account1.getLock().unlock();
            }
        } else {
            account1.incFailedTransferCount();
            account2.incFailedTransferCount();
            System.out.println("Error waiting lock");
            return false;
        }
    }

}
