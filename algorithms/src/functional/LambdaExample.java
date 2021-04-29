package functional;

public class LambdaExample {
    public static void main(String[] args) {
        new Thread( () -> System.out.println("Printing from the Runnable")).start();

    }
}

class CodeToRun implements Runnable {

    @Override
    public void run() {
        System.out.println("Printing the runnable");
    }
}

//traditional way
//where is created class CodeToRun.class which is implementing Runnable interface
//class CodeToRun implements Runnable {
//
//    @Override
//    public void run() {
//        System.out.println("Printing the runnable");
//    }
//}
//
//
//public class LambdaExample {
//    public static void main(String[] args) {
//        new Thread(new CodeToRun()).start();
//
//    }
//}
//
//
//class CodeToRun implements Runnable {
//
//    @Override
//    public void run() {
//        System.out.println("Printing the runnable");
//    }
//}

//Second approach with anonymous class

//package functional;
//
//public class LambdaExample {
//    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("printing from the Runnable");
//            }
//        }).start();
//
//    }
//}
//
//
//class CodeToRun implements Runnable {
//
//    @Override
//    public void run() {
//        System.out.println("Printing the runnable");
//
//    }
//}


