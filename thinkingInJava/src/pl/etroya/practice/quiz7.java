package pl.etroya.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class quiz7 {
    public static void main(String[] args) {
        Consumer<Integer> lambda1 = (x) -> {
            x++;
            System.out.print(x);
        };

//        Function<Double, Double> lambda = r -> {
//            double circumference = 2*Math.PI*r;
//        };


//        BiFunction<Integer, Integer, String> lambda3= String::substring;
    }

}
