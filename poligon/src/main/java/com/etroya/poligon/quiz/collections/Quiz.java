package com.etroya.poligon.quiz.collections;

import java.util.Arrays;

class Quiz{
public static void main (String ... args)  {
    String[] namesA = {"A","AA","AAA","AAAA"};
    Arrays.sort(namesA, new Compare());
    for (String name: namesA) {
        System.out.println(name);
    }


    String[] namesB = {"BBBB","BBB","BB","B"};
    Arrays.sort(namesB, new Compare());
    for (String name: namesB) {
        System.out.println(name);
    }



}


}
