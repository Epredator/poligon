package pl.etroya.design.facade;

import java.sql.Connection;

public class DbDbSingeltonForFacade {
    private static DbDbSingeltonForFacade instance;

    public static DbDbSingeltonForFacade getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return null;
    }
}
