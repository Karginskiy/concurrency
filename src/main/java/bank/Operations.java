package bank;

import bank.exceptions.InsufficientFundsException;

public class Operations {

    public static void main(String[] args) {

        final Account a = new Account(1000);
        final Account b = new Account(2000);

        new Thread(() -> {
            try {
                transfer(a, b, 500);
                System.out.println(a.getBalance());
            } catch (InsufficientFundsException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                transfer(b, a, 300);
                System.out.println(b.getBalance());
            } catch (InsufficientFundsException e) {
                e.printStackTrace();
            }
        }).start();

    }

    private static void transfer(Account account1, Account account2, int amount) throws InsufficientFundsException {


    }

}
