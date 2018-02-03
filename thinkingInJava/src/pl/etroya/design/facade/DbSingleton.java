package pl.etroya.design.facade;

import java.sql.Connection;

public class DbSingleton {
    private static DbSingleton instance;

    public static DbSingleton getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return null;
    }
}
