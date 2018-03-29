package pl.etroya.corejava.multithreading;

public class BankAccount {
    private final String id;
    private int balance;

    public BankAccount(String id, int startBalance) {
        this.id = id;
        balance = startBalance;
    }

    public BankAccount(int startBalance) {
        balance = startBalance;
        id = new String();
    }

    public synchronized int getBalance(){
        return balance;
    }

    public synchronized void deposit(int amount){
        balance += amount;
    }

    public synchronized  void withdrawal(int amount){
        balance -= amount;
    }

    public String getId() {
        return id;
    }
}
