package pl.etroya.corejava.serialization;

import java.io.Serializable;

public class BankAccount implements Serializable {
    private final String id;
    private final int balance;

    public String getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public BankAccount(String id, int balance) {
        this.id = id;
        this.balance = balance;

    }

    public void deposit(int i) {

    }
}