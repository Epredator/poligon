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

        ArrayList<String> names = new ArrayList<>();
        names.add("Czesław");
        names.add("Bolesław");
        names.add("Wiesław");
        names.add("Jan");
        names.add("Adam");
        names.add("Stefan");

        List<Integer> filteredL = l.stream().filter(i -> i%2==0).collect(Collectors.toList());
        List<Integer> multiplicationL = l.stream().map(i -> i*2).collect(Collectors.toList());
        List<String> namesLessThen5 = names.stream().filter(i -> i.length()>5).collect(Collectors.toList());
        List<String> sortedNumbers = names.stream().sorted().collect(Collectors.toList());
        Integer[] array = names.stream().toArray(Integer[]:: new);
        List<String> sortedRevertNumbers = names.stream().sorted((i1, i2) -> -i1.compareTo(i2)).collect(Collectors.toList());
        long size = names.stream().filter(i -> i.length()>5).count();
        List<String> upperCase = names.stream().map(i -> i.toUpperCase()).collect(Collectors.toList());
        String minVal = names.stream().min(String::compareTo).get();
        String maxVal = names.stream().max(String::compareTo).get();
        namesLessThen5.stream().forEach(s -> System.out.println(s));
        sortedNumbers.stream().forEach(System.out::println);
        System.out.println("printed minVal value is " + minVal);
        System.out.println("printed maxVal value is " + maxVal);
        System.out.println(filteredL.toString());
        System.out.println(multiplicationL.toString());
        System.out.println(namesLessThen5.toString());
        System.out.println(upperCase.toString());
        System.out.println(sortedNumbers.toString());
        System.out.println(sortedRevertNumbers.toString());
        System.out.println(size);
    }



}
