package com.etroya.learning.rxjava.java8;

import java.util.stream.IntStream;

//sample code from: Learn Lambdas, Streams , new Date APIs, Optionals and Parallel programming in Java 8 by coding it.
//url: https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/

public class ImperativeVsDeclarativeEx1 {

    public static void main(String[] args) {
        declarative();
        imperative();
    }

    public static void declarative(){
        int sum = 0;
        for(int i = 0; i<=100; i++){
            sum+=i;
        }
        System.out.println("Sum declarative: " + sum);
    }

    public static void imperative(){
        int sum = IntStream.rangeClosed(1,100)
                .sum();
        System.out.println("Sum imperative: " + sum);
    }

}
