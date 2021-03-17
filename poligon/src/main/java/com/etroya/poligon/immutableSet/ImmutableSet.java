package com.etroya.poligon.immutableSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableSet {

    private void addToImmutableSet(){
        Set<String> set = new HashSet<>();
        set.add("Canada");
        set.add("USA");

        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);

    }

}
