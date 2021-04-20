package pl.etroya.practice;

import java.util.function.BiPredicate;
import java.util.function.DoubleConsumer;
import java.util.function.Function;

public class quiz4 {
    public static void main(String... args) {
    }

//    first bad (abstract methods cannot have a body)
    public abstract class testClass1 {
        double taxRate = 0.05;
        abstract void increaseTax();
//            taxRate = taxRate + 0.01;

    }

//    drugie dobrze
    public interface TestInterface1 {
        static double taxRate = 0.05;
        abstract void increaseTax();
    }


//    third bad (Cannot assign a value to final variable 'taxRate')
    public interface TestInterface2 {
        double taxRate = 0.05;
        default void increaseTax() {
//            taxRate = taxRate + 0.01;
        }
    }

//    4 dobre
    public interface TestInterface {
        double taxRate = 0.05;
        void increaseTax();
    }

//    5 dobre
    public abstract class testClass {
        double taxRate = 0.05;
        public void increaseTax() {
            taxRate = taxRate + 0.01;
        }
    }





}
