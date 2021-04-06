package com.etroya.poligon.quiz.collections;

import java.util.Comparator;

public class Compare implements Comparator<String> {
    public int compare(String s1, String s2) {
        int len = s2.length() - s1.length();
        System.out.println("Comparison between "+s1+ " and " + s2 + " is " + len );
        return len;
    }
}


