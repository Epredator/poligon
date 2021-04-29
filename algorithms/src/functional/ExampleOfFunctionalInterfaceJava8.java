package functional;

@FunctionalInterface
interface InterfJava8 {
    public void add (int a, int b);
}




class MainJava8{
    public static void main(String[] args) {
        Interf sum = (a, b) -> System.out.println("The sum of " + (a + b));
        sum.add(10,29);
        sum.add(120,29);


    }
}
