package com.etroya.learning.rxjava.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//sample code from: Learn Lambdas, Streams , new Date APIs, Optionals and Parallel programming in Java 8 by coding it.
//url: https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/

public class ImperativeVsDeclarativeEx2 {

    public static void main(String[] args) {
        declarative();
        imperative();
    }

    public static void declarative() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> uniqueNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            if (!uniqueNumbers.contains((number))) {
                uniqueNumbers.add(number);
            }
        }
        System.out.println("uniqueNumber in declarative approach: " + uniqueNumbers);

    }


    public static void imperative() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> uniqueNumbers =  numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("uniqueNumber in imperative approach: " + uniqueNumbers);
    }

}
