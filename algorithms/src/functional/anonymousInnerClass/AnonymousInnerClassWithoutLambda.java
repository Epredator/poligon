package functional.anonymousInnerClass;

public class AnonymousInnerClassWithoutLambda {
    public static void main(String[] args) {
        Runnable a  = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<10; i++){
                    System.out.println("Child thread");
                }
            }
        };
        Thread t = new Thread();
        t.start();
        for(int i = 0; i<10; i++){
            System.out.println("Main thread");
        }
    }
}

