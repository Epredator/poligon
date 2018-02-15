package pl.etroya.design.crational.singelton;

public class DbSingelton {
    private static DbSingelton instance = null;

    private DbSingelton(){

    }

    public static DbSingelton getInstance() {
        if(instance.equals(null)){
            instance = new DbSingelton();
        }
        return instance;
    }
}
