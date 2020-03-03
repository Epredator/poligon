package functional;

@FunctionalInterface
interface Interf {
    public void add(int a, int b);

}

public class ExampleOfFunctionalInterfaceJava7 implements Interf {
    @Override
    public void add(int a, int b) {
        System.out.println("The sum of " + (a+b));

    }

}

class Main{
    public static void main(String[] args) {
        Interf i = new ExampleOfFunctionalInterfaceJava7();
        i.add(10,20);
        i.add(120,220);
    }
}
