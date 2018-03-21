package pl.etroya.corejava.collection;

import java.util.TreeSet;

public class ComparatorCollectionEx {
        public static void main(String[] args){
            TreeSet<ComparatorCollectionApi> tree = new TreeSet<>();
            tree.add(new ComparatorCollectionApi("2222", "ghi"));
            tree.add(new ComparatorCollectionApi("3333", "abc"));
            tree.add(new ComparatorCollectionApi("1111", "def"));

            tree.forEach(m -> System.out.println(m));
        }
}
