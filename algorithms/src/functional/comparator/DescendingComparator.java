package functional.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DescendingComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer i1, Integer i2) {

        if (i1 > i2) {
            return -1;

        } else if (i1 < i2) {
            return 1;
        } else {
            return 0;
        }
    }
}

//public class TurnerAlternativeWayDescendingComparator implements Comparator<Integer> {
//    @Override
//    public int compare(Integer i1, Integer i2) {
//
//        return (i1 > i2) ? -1 : (i1 < i2) ? 1 : 0;
//    }
//}

class Test{
    public static void main(String[] args) {
        ArrayList list = new ArrayList<Integer>();
        list.add(10);
        list.add(12);
        list.add(1);
        list.add(12);
        System.out.println("Before sortng: " + list);

        Collections.sort(list, new DescendingComparator());

        System.out.println("After sortng: " + list);
    }
}
