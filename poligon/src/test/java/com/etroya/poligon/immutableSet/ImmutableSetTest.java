package com.etroya.poligon.immutableSet;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableSetTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSet() {
        Set<String> set = new HashSet<>();
        set.add("Canada");
        set.add("USA");

        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
        unmodifiableSet.add("Costa Rica");

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testCopyOfUnmodifiableSet() {
        Set<String> set = new HashSet<>();
        set.add("Canada");
        set.add("USA");

        Set<String> immutable = ImmutableSet.copyOf(set);

        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
        unmodifiableSet.add("Costa Rica");

    }

}