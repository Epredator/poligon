package pl.etroya.design.facade;

public class DbSingleton {
    private static DbSingleton instance;

    public static DbSingleton getInstance() {
        return instance;
    }
}
