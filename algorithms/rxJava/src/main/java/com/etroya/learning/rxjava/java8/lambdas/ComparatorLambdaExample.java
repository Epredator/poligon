package com.etroya.learning.rxjava.java8.lambdas;

import java.util.Comparator;

//sample code from: Learn Lambdas, Streams , new Date APIs, Optionals and Parallel programming in Java 8 by coding it.
//url: https://www.udemy.com/course/modern-java-learn-java-8-features-by-coding-it/

public class ComparatorLambdaExample {

    public static void main(String[] args) {
        priorJava8();
        afterJava8();
    }

    public static void priorJava8() {
        Comparator comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);    //0 -> o1 == o2,
                                            // 1 -> o1 > o2
                                            // -1 -> o1 < o2,
            }
        };
        System.out.println("Result of the comparator is: " + comparator.compare(4, 3));
    }

    public static void afterJava8() {
        Comparator<Integer> comparatorLambda = (Integer a, Integer b) -> a.compareTo(b);
        System.out.println("Result of the comparator using lanbda is: " + comparatorLambda.compare(4, 3));

        Comparator<Integer> comparatorLambdaAnother = (a,  b) -> a.compareTo(b);
        System.out.println("Result of the comparator using lanbda is: " + comparatorLambdaAnother.compare(4, 3));

    }

}
