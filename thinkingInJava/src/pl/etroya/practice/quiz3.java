package pl.etroya.practice;

import java.util.function.BiPredicate;
import java.util.function.DoubleConsumer;
import java.util.function.Function;

public class quiz3 {
    public static void main(String... args) {
        BiPredicate<Integer, Integer> test = (x, y) -> x == y;
//        BiPredicate<Integer, Integer> add = (x, y) -> x + y;
        DoubleConsumer cube = x -> System.out.println(x*x*x);
        DoubleConsumer display = a -> System.out.println(a * 10);
        Function<String, Integer> hello = (x) -> 1;
        System.out.println(hello.apply("aaa"));
    }
}
