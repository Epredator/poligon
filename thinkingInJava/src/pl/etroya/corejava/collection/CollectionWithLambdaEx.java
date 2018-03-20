package pl.etroya.corejava.collection;

import java.util.ArrayList;

public class CollectionWithLambdaEx {
    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();

        //TODO why I cannot assign elements to list
        String v1 = new String("abc");
        String v2 = new String("xyz");
        String v3 = new String("abc");

        list.add(v1);
        list.add(v2);
        list.add(v3);



       list.forEach(m -> System.out.println(m.toString()));
       list.removeIf(m -> m.equals("abc") );
       list.forEach(m -> System.out.println(m.toString()));



    }
}
