package pl.etroya.practice.quiz11;

public interface TheInterface {
    int field = 99;

    private void method() {
        System.out.println("aaa");
    }

    default void method1(){
        System.out.println("a");
    }

    public static void a1(){
        System.out.println("aaa");
    }
}