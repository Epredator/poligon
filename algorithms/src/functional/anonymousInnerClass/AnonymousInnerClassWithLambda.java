package functional.anonymousInnerClass;

public class AnonymousInnerClassWithLambda {
    public static void main(String[] args) {
        Runnable a  = () -> {
            for(int i = 0; i<10; i++){
                System.out.println("Child thread");
            }
        };
        Thread t = new Thread(a);
        t.start();
        for(int i = 0; i<10; i++){
            System.out.println("Main thread");
        }
    }
}
