package pl.etroya.design.crational.singelton;


public class DbSingeltoDemo {
    public static void main(String[] args){
        DbSingelton instance = DbSingelton.getInstance();
        System.out.println(instance);

        DbSingelton secondInstance = DbSingelton.getInstance();
        System.out.println(secondInstance);

    }
}
