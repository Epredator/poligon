package pl.etroya.design.crational.singleton;

public class SingletonSample {
    public static void main(String args[]){
        Runtime singletonRuntime = Runtime.getRuntime();
        singletonRuntime.gc();
        System.out.println(singletonRuntime);

        Runtime anotherInstancje = Runtime.getRuntime();
        System.out.println(anotherInstancje);

        if (singletonRuntime == anotherInstancje){
            System.out.println("The are the same instance!");
        }
    }
}
