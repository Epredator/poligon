//package pl.etroya.corejava.collection;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//
//
//class ImmutableSetTest {
//
//    @Test(expected = UnsupportedOperationException.class)
//    void main() {
//        //create and initialize the instance
//        Set<String> set = new HashSet<>();
//        set.add("Warsaw");
//        set.add("Cracow");
//
//        Set<String> unmodifiableSet = Collections.unmodifiableSet(set);
//        unmodifiableSet.add("Poznań");
//    }
//}