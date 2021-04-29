package functional.defaultMethods;

public interface SampleInterf {
    default void m1() {
        System.out.println("sample interface");
    }
}
