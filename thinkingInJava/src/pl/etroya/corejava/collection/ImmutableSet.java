package pl.etroya.corejava.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableSet {
    public static void main(String[] args){
        Set<String> set = new HashSet<>();
        set.add("Warsaw");
        set.add("Cracow");

        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);

    }
}
