package oca;

//Question 6: Incorrect
//        Consider the following code:
//https://nordea.udemy.com/course/oracle-java-certification-shortest-way-to-crack-oca-1z0-808/learn/quiz/4451980/result/315113244#overview

public class PracticeTest3Question6 {
    PracticeTest3Question6(Integer I) {
        System.out.print(0);
    }

    PracticeTest3Question6(int... i) {
        System.out.print(1);
    }

    PracticeTest3Question6(double d) {
        System.out.print(2);
    }

    PracticeTest3Question6(Object o) {
        System.out.print(3);
    }

    public static void main(String[] args) {
        new PracticeTest3Question6('a');
        new PracticeTest3Question6(10);
        new PracticeTest3Question6(10.5);
    }
}

//Explanation
//        While resolving method calls, compiler will always gives the precedence in the following order.
//        1. Exact Match
//        2. Widening
//        3. Auto-Boxing
//        4. var-arg
//        new Test('a');==>Exact Match is not there and hence by widening double-arg constructor will get chance.
//        new Test(10);==>Exact Match is not there and hence by widening double-arg constructor will get chance.
//        new Test(10.5);===>Exact Match is available and hence by double-arg constructor will get chance.
