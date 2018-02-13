package pl.etroya.design.crational.singelton;

public class DbSingelton {
    private static DbSingelton instance = new DbSingelton();

    private DbSingelton(){

    }

    public static DbSingelton getInstance() {
        return instance;
    }
}
