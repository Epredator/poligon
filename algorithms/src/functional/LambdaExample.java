package functional;

public class LambdaExample {
    public static void main(String[] args) {
        new Thread(new CodeToRun()).start();

    }
}


class CodeToRun implements Runnable {

    @Override
    public void run() {
        System.out.println("Printing the runnable");
    }
}

