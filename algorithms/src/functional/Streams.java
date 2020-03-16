package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(3);
        l.add(32);
        l.add(54);
        l.add(1);
        l.add(324);
        l.add(12);
        System.out.println(l.toString());

        List<Integer> filteredL = l.stream().filter(i -> i%2==0).collect(Collectors.toList());
        System.out.println(filteredL.toString());
    }
}
