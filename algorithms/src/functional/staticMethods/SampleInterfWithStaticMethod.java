package functional.staticMethods;

public interface SampleInterfWithStaticMethod {
    public static void m() {
        System.out.println("interface with static method");
    }
}

class SampleInterfImpl implements SampleInterfWithStaticMethod {
    public static void main(String[] args) {
        SampleInterfImpl sii = new SampleInterfImpl();

        SampleInterfWithStaticMethod.m(); //interface name
    }

}
