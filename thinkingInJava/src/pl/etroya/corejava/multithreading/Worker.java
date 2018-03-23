package pl.etroya.corejava.multithreading;

public class Worker implements Runnable {
    private BankAccount account;

    public Worker(BankAccount account){
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int startBalance = account.getBalance();
            account.deposit(10);
            int endBalance = account.getBalance();
        }

    }

    private class BankAccount {
        private int balance;

        public BankAccount(int startBalance){
            balance = startBalance;
        }

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            balance += amount;

        }
    }
}
