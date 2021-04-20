package pl.etroya.practice;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;

public class quiz5 {
    public static void main(String... args) {
//     First bad   (Type 'java.util.function.DoublePredicate' does not have type parameters)
//        DoublePredicate test = (x, y) -> x == y;

        DoubleFunction<Double> area = r -> Math.PI*r*r;

        IntPredicate test = x -> x == 10;

        BiFunction<Integer, Integer, Integer> divide = (x, y) -> x/y;
    }

}
