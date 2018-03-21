package pl.etroya.corejava.collection;

import java.util.TreeSet;

public class ComparableCollectionEx {
    public static void main(String[] args){
        TreeSet<ComparableCollectionApi> tree = new TreeSet<>();
        tree.add(new ComparableCollectionApi("2222", "ghi"));
        tree.add(new ComparableCollectionApi("3333", "abc"));
        tree.add(new ComparableCollectionApi("1111", "def"));

        tree.forEach(m -> System.out.println(m));
    }
}
