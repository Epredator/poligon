package pl.etroya.corejava.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.*;

public class Main {
    static Logger logger = Logger.getLogger("pl.etroya.corejava.serialization");

    public static void main(String[] args) {
        Handler h = new ConsoleHandler();
        Formatter f = new SimpleFormatter();
        h.setFormatter(f);
        logger.addHandler(h);
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "We are logging");

        BankAccount acc = new BankAccount("1234", 600);
        acc.deposit(250);
        saveAccount(acc, "account.dat");
        loadAccount("account.dat");
        System.out.println("id: " + acc.getId() + " balance: " + acc.getBalance());
    }

    private static void saveAccount(BankAccount sa, String filename) {
        try (ObjectOutputStream objectStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            objectStream.writeObject(sa);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static BankAccount loadAccount(String filename) {
        BankAccount ba = null;
        try (ObjectInputStream objectStream = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            ba = (BankAccount) objectStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ba;

    }
}
