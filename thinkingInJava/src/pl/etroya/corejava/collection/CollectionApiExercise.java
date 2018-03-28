package pl.etroya.corejava.collection;

import java.util.ArrayList;
import java.util.List;

public class CollectionApiExercise {
    public static void main(String[] args){
        ArrayList<CollectionApi> list = new ArrayList<>();

        //TODO why I cannot assign elements to list
        CollectionApi v1 = new CollectionApi("v1", "abc");
        CollectionApi v2 = new CollectionApi("v2", "abc");
        CollectionApi v3 = new CollectionApi("v3", "abc");

        list.add(v1);
        list.add(v2);
        list.add(v3);

        list.remove(v3);

        for(CollectionApi l : list)
            System.out.println(l.label);
    }
}
