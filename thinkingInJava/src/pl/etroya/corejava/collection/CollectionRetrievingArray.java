package pl.etroya.corejava.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionRetrievingArray {
    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("xyz");

        Object[] objArray = list.toArray();
        String[] s1 = list.toArray(new String[0]);
        String[] s2 = list.toArray(new String[3]);
        String[] s3 = list.toArray(s2);

        if(s2 == s3)
            System.out.println("a2 & a3 reference the same array");

        Collection<String> backToCollectionList = Arrays.asList(s3);
        list.forEach(System.out::println);
        backToCollectionList.forEach(System.out::println);
    }
}
